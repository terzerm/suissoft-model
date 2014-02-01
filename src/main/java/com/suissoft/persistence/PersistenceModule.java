package com.suissoft.persistence;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;

/**
 * Guice injection module. Binds {@link EntityManagerFactory} and {@link EntityManager}
 * to concrete instances. The factories and managers to be injected must be annotated
 * with the appropriate annotation defined in {@link PersistenceUnit}. 
 */
@javax.persistence.PersistenceUnit
public class PersistenceModule extends AbstractModule {
	
	private final PersistenceUnit persistenceUnit;
	
	public PersistenceModule(PersistenceUnit persistenceUnit) {
		Objects.requireNonNull(persistenceUnit, "persistenceUnit cannot be null");
		this.persistenceUnit = persistenceUnit;
	}

	@Override
	protected void configure() {
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnit.name());
		bind(PersistenceUnit.class).toInstance(persistenceUnit);
		bind(EntityManagerFactory.class).toInstance(factory);
		install(new DaoModule(factory));
	}
	
}
