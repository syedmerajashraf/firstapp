package my.json;

/*
* Copyright (c) 2011 Fidelis, Inc. All Rights Reserved.
*
* This software is the proprietary information of Fidelis, Inc.
* Use is subject to license terms.
*  * @author kyrill007
 * URL - http://kyrill007.livejournal.com/2577.html?thread=4369
*/

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

/**
 * This class extends {@code ObjectMapper} class of the Jackson framework to provide
 * minor customizations:
 * <ul>
 * <li>To set a custom {@link HibernateAwareSerializerFactory}</li>
 * <li>To relax Jackson handling of unknown class types</li>
 * </ul>
 * <p/>
 * <em>Note:</em> Due to the nature {@code ObjectMapper} class.
 * those customization could not be done through the Spring Framework..
 *
 * @author Kyrill Alyoshin
 * @see HibernateAwareSerializerFactory
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

	
    public HibernateAwareObjectMapper() {
        setSerializerFactory(new HibernateAwareSerializerFactory(null));
        configure(Feature.FAIL_ON_EMPTY_BEANS, false);
        this.getJsonFactory().setCharacterEscapes(new HTMLCharacterEscapes());
        System.out.println(this.getJsonFactory().getCharacterEscapes());
       
    }

    public void setPrettyPrint(boolean prettyPrint) {
        configure(Feature.INDENT_OUTPUT, prettyPrint);
    }
}
