package com.suissoft.model.guice;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.suissoft.model.entity.dao.Dao;
import com.suissoft.model.util.PersistenceUnit;

/**
 * Guice injection module. Binds {@link EntityManagerFactory} and {@link Dao}s
 * to concrete instances. 
 */
@javax.persistence.PersistenceUnit
public class EntityManagerModule extends AbstractModule {
	
	private final EntityManagerFactory entityManagerFactory;

	public EntityManagerModule(PersistenceUnit persistenceUnit) {
		this(Persistence.createEntityManagerFactory(persistenceUnit.name()));
	}
	public EntityManagerModule(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	protected void configure() {
		bind(EntityManagerFactory.class).toInstance(entityManagerFactory);
		install(new DaoModule(entityManagerFactory));
	}

}
