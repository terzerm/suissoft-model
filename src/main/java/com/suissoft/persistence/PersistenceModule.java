package com.suissoft.persistence;

import java.util.EnumMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

/**
 * Guice injection module. Binds {@link EntityManagerFactory} and {@link EntityManager}
 * to concrete instances. The factories and managers to be injected must be annotated
 * with the appropriate annotation defined in {@link PersistenceUnit}. 
 */
public class PersistenceModule extends AbstractModule {
	
	private final PersistenceUnit[] persistenceUnits;
	
	public PersistenceModule() {
		this(PersistenceUnit.values());
	}
	public PersistenceModule(PersistenceUnit... persistenceUnits) {
		this.persistenceUnits = persistenceUnits;
	}

	@Override
	protected void configure() {
		final Map<PersistenceUnit, EntityManagerFactory> factories = new EnumMap<>(PersistenceUnit.class);
		final Map<PersistenceUnit, EntityManager> managers = new EnumMap<>(PersistenceUnit.class);
		for (final PersistenceUnit persistenceUnit : persistenceUnits) {
			final EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnit.name());
			final EntityManager manager = factory.createEntityManager();
			
			factories.put(persistenceUnit, factory);
			managers.put(persistenceUnit, manager);
			
			bind(EntityManagerFactory.class)
			.annotatedWith(persistenceUnit.getAnnotation())
			.toInstance(factory);

			bind(EntityManager.class)
			.annotatedWith(persistenceUnit.getAnnotation())
			.toInstance(manager);
			
			install(new DaoModule(persistenceUnit, manager));
		}
		bind(new TypeLiteral<Map<PersistenceUnit, EntityManagerFactory>>(){}).toInstance(factories);
		bind(new TypeLiteral<Map<PersistenceUnit, EntityManager>>(){}).toInstance(managers);
	}
	
}
