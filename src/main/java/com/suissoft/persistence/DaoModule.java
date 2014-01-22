package com.suissoft.persistence;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.EntityManagerDao;

class DaoModule extends AbstractModule {
	
	private final PersistenceUnit peristenceUnit;
	private final EntityManager entityManager;

	@Inject
	public DaoModule(PersistenceUnit peristenceUnit, EntityManager entityManager) {
		this.peristenceUnit = peristenceUnit;
		this.entityManager = entityManager; 
	}

	@Override
	protected void configure() {
		for (final EntityType<?> entityType : entityManager.getMetamodel().getEntities()) {
			bindDaoFor(entityType.getJavaType());
		}
	}
	
	private <T> void bindDaoFor(Class<T> entityClass) {
		final Dao<T> dao = new EntityManagerDao<>(entityClass, entityManager);
		bind(getDaoTypeLiteralFor(entityClass)).annotatedWith(peristenceUnit.getAnnotation()).toInstance(dao);
	}
	
	@SuppressWarnings({ "serial", "unchecked" })
	private static <T> TypeLiteral<Dao<T>> getDaoTypeLiteralFor(Class<T> entityClass) {
		final Type type = new TypeToken<Dao<T>>(){}.where(new TypeParameter<T>(){}, entityClass).getType();
		return (TypeLiteral<Dao<T>>) TypeLiteral.get(type);
	}
	
}
