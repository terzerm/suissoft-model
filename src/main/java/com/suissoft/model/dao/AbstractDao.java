package com.suissoft.model.dao;

import static java.util.Objects.requireNonNull;

abstract public class AbstractDao<E> implements Dao<E> {
	
	private Class<E> entityClass;

	public AbstractDao(Class<E> entityClass) {
		requireNonNull(entityClass, "entityClass cannot be null");
		this.entityClass = entityClass;
	}
	
	@Override
	public Class<E> getEntityClass() {
		return entityClass;
	}

	@Override
	public E create() {
		try {
			return entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("create for " + entityClass.getName() + " failed, e=" + e, e);
		}
	}

	@Override
	public boolean delete(long id) {
		final E entity = findById(id);
		if (entity != null) {
			delete(entity);
			return true;
		}
		return false;
	}

}
