package com.fidelis.k2.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDaoImpl<T>  implements BaseDao<T>{

	protected Class<T> type;
	@PersistenceContext
	protected EntityManager entityManager;

	public BaseDaoImpl(Class<T> type) {
		super();
		this.type = type;
	}

	protected Session getCurrentSession(){
		return entityManager.unwrap(Session.class);
	}

	@Transactional
	@Override
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	@Override
	public void save(Collection<T> entities){
		for(T entity : entities){
			this.save(entity);
		}
	}

	@Transactional
	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@Transactional
	@Override
	public void remove(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public T findById(int entityId) {
		return (T) entityManager.find(type, entityId);
	}

	@Transactional(readOnly=true)
	@Override
	public T load(Object id) {
		if (id == null) {
			return null;
		} else {
			return entityManager.find(type, id);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<T> findAll() {
		return entityManager.createQuery("select o from " + type.getName() + " o").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getResults(String queryStr){
		return entityManager.createQuery(queryStr).getResultList();
	}

	@Override
	public void refresh(T object) {
		entityManager.refresh(object);
	}

	@Override
	public void flush() {
		entityManager.flush();
	}

	@Override
	public void clearEntityManager() {
		entityManager.clear();
	}

}
