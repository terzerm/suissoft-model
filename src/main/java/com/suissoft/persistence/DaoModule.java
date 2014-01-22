package com.suissoft.persistence;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;
import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.EntityManagerDao;
import com.suissoft.persistence.unit.Persistence;

class DaoModule extends AbstractModule {
	
	private final Persistence.Unit peristenceUnit;
	private final EntityManager entityManager;

	@Inject
	public DaoModule(Persistence.Unit peristenceUnit, EntityManager entityManager) {
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
		bind(getDaoTypeLiteralFor(entityClass)).annotatedWith(peristenceUnit.asAnnotation()).toInstance(dao);
	
	}
	
	@SuppressWarnings("unchecked")
	private static <T> TypeLiteral<Dao<T>> getDaoTypeLiteralFor(Class<T> entityClass) {
		final Type type = Types.newParameterizedType(Dao.class, entityClass); 
		return (TypeLiteral<Dao<T>>) TypeLiteral.get(type);
	}
	
}
