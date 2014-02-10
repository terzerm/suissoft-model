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
import com.suissoft.model.dao.UseDao;
import com.suissoft.persistence.dao.EntityManagerDao;

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
		final TypeLiteral<Dao<E>> daoTypeLiteral = getDaoTypeLiteralFor(entityClass);
		final UseDao useDao = entityClass.getAnnotation(UseDao.class);
		if (useDao == null) {
			final Dao<E> dao = new EntityManagerDao<>(entityClass, entityManagerFactory);
			bind(daoTypeLiteral).toInstance(dao);
		} else {
			bindSpecialisedDao(entityClass, daoTypeLiteral, castDaoType(entityClass, daoTypeLiteral, useDao.type()), useDao);
		}
	}

	private <E extends Entity, D extends Dao<E>> void bindSpecialisedDao(Class<E> entityClass, TypeLiteral<Dao<E>> daoTypeLiteral, Class<D> daoType, UseDao useDao) {
		bindSpecialisedDao(daoTypeLiteral, daoType, castDaoImpl(entityClass, daoTypeLiteral, daoType, useDao.impl()));
	}

	private <E extends Entity, D extends Dao<E>> void bindSpecialisedDao(TypeLiteral<Dao<E>> daoTypeLiteral, Class<D> daoType, Class<? extends D> daoImpl) {
		bind(daoTypeLiteral).to(daoImpl);
		if (!Dao.class.equals(daoType)) {
			bind(daoType).to(daoImpl);
		}
	}

	private static <E extends Entity, D extends Dao<E>> Class<D> castDaoType(Class<E> entityClass, TypeLiteral<Dao<E>> daoTypeLiteral, Class<? extends Dao<?>> daoType) {
		if (Dao.class.equals(daoType)) {
			//that's ok, interface is simply the Dao itself
			//safe cast now because Dao<E> clearly extends Dao<E>
			@SuppressWarnings("unchecked")
			final Class<D> result = (Class<D>)daoType;
			return result;
		}
		for (Type type : daoType.getGenericInterfaces()) {
			if (type.equals(daoTypeLiteral.getType())) {
				//safe cast now because the literal types are identical
				@SuppressWarnings("unchecked")
				final Class<D> result = (Class<D>)daoType;
				return result;
			}
			//TODO recurse for super types
		}
		throw new IllegalArgumentException("dao type " + daoType.getName() + " specified in @" + UseDao.class.getSimpleName() + " for entity " + entityClass.getName() + " must implement " + daoTypeLiteral);
	}

	private static <E extends Entity, D extends Dao<E>> Class<? extends D> castDaoImpl(Class<E> entityClass, TypeLiteral<Dao<E>> daoTypeLiteral, Class<D> daoType, Class<? extends Dao<?>> daoImpl) {
		if (daoType.isAssignableFrom(daoImpl)) {
			//ok, good start
			//and as we have checked daoType already, we can safely cast now
			@SuppressWarnings("unchecked")
			final Class<? extends D> result = (Class<? extends D>)daoImpl;
			return result;
		}
		throw new IllegalArgumentException("dao impl " + daoImpl.getName() + " specified in @" + UseDao.class.getSimpleName() + " for entity " + entityClass.getName() + " must implement dao type " + daoType.getName());
	}

	@SuppressWarnings("unchecked")
	private static <E extends Entity> TypeLiteral<Dao<E>> getDaoTypeLiteralFor(Class<E> entityClass) {
		final Type type = Types.newParameterizedType(Dao.class, entityClass);
		return (TypeLiteral<Dao<E>>) TypeLiteral.get(type);
	}

}
