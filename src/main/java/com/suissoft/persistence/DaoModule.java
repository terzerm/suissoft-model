package com.suissoft.persistence;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.EntityType;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;
import com.suissoft.model.Entity;
import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.EntityManagerDao;

@javax.persistence.PersistenceUnit
class DaoModule extends AbstractModule {
	
	private final EntityManagerFactory entityManagerFactory;

	@Inject
	public DaoModule(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory; 
	}

	@Override
	protected void configure() {
		for (final EntityType<?> entityType : entityManagerFactory.getMetamodel().getEntities()) {
			final Class<?> javaType = entityType.getJavaType();
			if (Entity.class.isAssignableFrom(javaType)) {
				bindDaoFor(javaType.asSubclass(Entity.class));
			} else {
				throw new PersistenceException("entity " + javaType.getName() + " must implement " + Entity.class.getName());
			}
		}
	}
	
	private <E extends Entity> void bindDaoFor(Class<E> entityClass) {
		final Dao<E> dao = new EntityManagerDao<>(entityClass, entityManagerFactory);
		bind(getDaoTypeLiteralFor(entityClass)).toInstance(dao);
	
	}
	
	@SuppressWarnings("unchecked")
	private static <E extends Entity> TypeLiteral<Dao<E>> getDaoTypeLiteralFor(Class<E> entityClass) {
		final Type type = Types.newParameterizedType(Dao.class, entityClass); 
		return (TypeLiteral<Dao<E>>) TypeLiteral.get(type);
	}
	
}
