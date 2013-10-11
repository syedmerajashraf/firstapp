package com.fidelis.k2.dao;

import java.util.Collection;
import java.util.List;

public interface BaseDao<T> {

	T update(T entity);
	void save(Collection<T> entities);
	void save(T entity);
	void remove(T entity);
	T findById(int entityId);
	T load(Object id);
	List<T> findAll();
	List<T> getResults(String queryStr);
	void refresh(T object);
	void flush();
	void clearEntityManager();
}
