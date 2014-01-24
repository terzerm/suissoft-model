package com.suissoft.persistence;

import java.util.EnumMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.suissoft.persistence.unit.Persistence;

/**
 * Guice injection module. Binds {@link EntityManagerFactory} and {@link EntityManager}
 * to concrete instances. The factories and managers to be injected must be annotated
 * with the appropriate annotation defined in {@link Persistence}. 
 */
public class PersistenceModule extends AbstractModule {
	
	private final Persistence.Unit[] persistenceUnits;
	
	public PersistenceModule() {
		this(Persistence.Unit.values());
	}
	public PersistenceModule(Persistence.Unit... persistenceUnits) {
		this.persistenceUnits = persistenceUnits;
	}

	@Override
	protected void configure() {
		final Map<Persistence.Unit, EntityManagerFactory> factories = new EnumMap<>(Persistence.Unit.class);
		final Map<Persistence.Unit, EntityManager> managers = new EnumMap<>(Persistence.Unit.class);
		for (final Persistence.Unit persistenceUnit : persistenceUnits) {
			final EntityManagerFactory factory = javax.persistence.Persistence.createEntityManagerFactory(persistenceUnit.name());
			final EntityManager manager = factory.createEntityManager();
			
			factories.put(persistenceUnit, factory);
			managers.put(persistenceUnit, manager);
			
			if (persistenceUnits.length > 1) {
				bind(EntityManagerFactory.class)
				.annotatedWith(persistenceUnit.asAnnotation())
				.toInstance(factory);
	
				bind(EntityManager.class)
				.annotatedWith(persistenceUnit.asAnnotation())
				.toInstance(manager);
			} else {
				bind(EntityManagerFactory.class)
				.toInstance(factory);
	
				bind(EntityManager.class)
				.toInstance(manager);
			}
			
			install(new DaoModule(persistenceUnit, factory));
		}
		bind(new TypeLiteral<Map<Persistence.Unit, EntityManagerFactory>>(){}).toInstance(factories);
		bind(new TypeLiteral<Map<Persistence.Unit, EntityManager>>(){}).toInstance(managers);
	}
	
}
