package my.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.type.JavaType;
import org.hibernate.bytecode.javassist.FieldHandled;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;



/**
 * @author kyrill007
 * URL - http://kyrill007.livejournal.com/2577.html?thread=4369
 * http://jira.codehaus.org/browse/JACKSON-576
 * This is the key class in enabling graceful handling of Hibernate managed entities when
 * serializing them to JSON.
 * <p/>
 * The key features are:
 * 1) Non-initialized properties will be rendered as {@code null} in JSON to prevent
 * "lazy-loaded" exceptions when the Hibernate session is closed.
 * 2) {@link Transient} properties not be rendered at all as they often present back door
 * references to non-initialized properties.
 *
 * @author Kyrill Alyoshin
 */
public class HibernateAwareSerializerFactory extends BeanSerializerFactory {
    /**
     * Name of the property added during build-time byte-code instrumentation
     * by Hibernate.
     */
    private static final String FIELD_HANDLER_PROPERTY_NAME = "fieldHandler";

    public HibernateAwareSerializerFactory(Config config) {
        super(config);
    }

    @Override
    public JsonSerializer<Object> createSerializer
            (SerializationConfig config, JavaType type, BeanProperty beanProperty) {
        Class<?> clazz = type.getRawClass();

        //check for all Hibernate proxy invariants and build custom serializers for them
        if (PersistentCollection.class.isAssignableFrom(clazz)) {
            return new PersistentCollectionSerializer(type, config, beanProperty);
        }

        if (HibernateProxy.class.isAssignableFrom(clazz)) {
            return new HibernateProxySerializer(type, config, beanProperty);
        }

        //Well, then it is not a Hibernate proxy
        try {
			return super.createSerializer(config, type, beanProperty);
		} catch (JsonMappingException e) {
//			new Util().logStackTrace(e);
		}
		return null;
    }

    /**
     * The purpose of this method is to filter out {@link Transient} properties of the bean
     * from JSON rendering.
     */
    @Override
    protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig config,
                                                            BasicBeanDescription beanDesc,
                                                            List<BeanPropertyWriter> props) {

        //filter out standard properties (e.g. those marked with @JsonIgnore)
        props = super.filterBeanProperties(config, beanDesc, props);

        filterInstrumentedBeanProperties(beanDesc, props);

        //now filter out the @Transient ones as they may trigger "lazy" exceptions by
        //referencing non-initialized properties
        List<String> transientOnes = new ArrayList();
/*        for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(beanDesc.getBeanClass())) {
            Method getter = pd.getReadMethod();
            if (getter != null
                    && AnnotationUtils.findAnnotation(getter, Transient.class) != null
                    && AnnotationUtils.findAnnotation(getter, JsonOk.class) == null) {
                transientOnes.add(pd.getName());
            }
        }*/

        //remove transient
        for (Iterator<BeanPropertyWriter> iter = props.iterator(); iter.hasNext();) {
            if (transientOnes.contains(iter.next().getName())) {
                iter.remove();
            }
        }

        return props;
    }

    private void filterInstrumentedBeanProperties(BasicBeanDescription beanDesc,
                                                  List<BeanPropertyWriter> props) {

        //all beans that have build-time instrumented lazy-loaded properties
        //will implement FieldHandled interface.
        if (!FieldHandled.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return;
        }

        //remove fieldHandler bean property from JSON serialization as it causes
        //infinite recursion
        for (Iterator<BeanPropertyWriter> iter = props.iterator(); iter.hasNext();) {
            if (iter.next().getName().equals(FIELD_HANDLER_PROPERTY_NAME)) {
                iter.remove();
            }
        }
    }

    /**
     * The purpose of this class is to perform graceful handling of custom Hibernate collections.
     */
    private class PersistentCollectionSerializer extends JsonSerializer<Object> {
        private final JavaType type;
        private final SerializationConfig config;
        private final BeanProperty beanProperty;

        private PersistentCollectionSerializer(JavaType type, SerializationConfig config, BeanProperty beanProperty) {
            this.type = type;
            this.config = config;
            this.beanProperty = beanProperty;
        }

        @Override
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            //avoid lazy initialization exceptions
            if (!((PersistentCollection) value).wasInitialized()) {
                jgen.writeNull();
                return;
            }

            //This if clause is needed as a workaround until:
            //http://jira.codehaus.org/browse/JACKSON-576
            //is fixed.
            if (type.isMapLikeType() && type.getKeyType().isEnumType()) {
                //must re-map type to EnumMap
                MapType effectiveType = MapType.construct(EnumMap.class, type.getKeyType(), type.getContentType());
                BasicBeanDescription beanDesc = config.introspect(effectiveType);

                JsonSerializer<Object> serializer =(JsonSerializer<Object>) buildContainerSerializer(config, effectiveType, beanDesc, beanProperty, false);

                Map<? extends Enum<?>, Object> suppliedValue = (Map<? extends Enum<?>, Object>)value;
                //must serialize as an enum map
                @SuppressWarnings("unchecked")
                Map<? extends Enum<?>, Object> effectiveValue = new EnumMap(suppliedValue);

                //delegate serialization to a built-in serializer
                serializer.serialize(effectiveValue, jgen, provider);
            }
            else {
                BasicBeanDescription beanDesc = config.introspect(type);
                JsonSerializer<Object> serializer = (JsonSerializer<Object>) buildContainerSerializer(config, type, beanDesc, beanProperty, false);
                //delegate serialization to a built-in serializer
                serializer.serialize(value, jgen, provider);
            }
        }
    }

    /**
     * The purpose of this class is to perform graceful handling of HibernateProxy objects.
     */
    private class HibernateProxySerializer extends JsonSerializer<Object> {
        private final JavaType type;
        private final SerializationConfig config;
        private final BeanProperty beanProperty;

        private HibernateProxySerializer(JavaType type, SerializationConfig config, BeanProperty beanProperty) {
            this.type = type;
            this.config = config;
            this.beanProperty = beanProperty;
        }

        @Override
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (((HibernateProxy) value).getHibernateLazyInitializer().isUninitialized()) {
                jgen.writeNull();
                return;
            }

            BasicBeanDescription beanDesc = config.introspect(type);
            JsonSerializer<Object> serializer = findBeanSerializer(config, type, beanDesc, beanProperty);

            //delegate serialization to a build-in serializer
            serializer.serialize(value, jgen, provider);
        }
    }

}