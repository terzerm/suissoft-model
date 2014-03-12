package com.suissoft.model.cdi;

import static com.suissoft.model.util.PersistenceUnit.H2_MEMORY;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;

/**
 * Used in CDI unit tests to produce a {@link EntityManagerFactory} for the
 * {@link #H2_MEMORY} database.
 */
@ApplicationScoped
public class EntityManagerProducer {
	@Produces
	@ApplicationScoped
	public EntityManagerFactory getEntityManagerFactory() {
		return H2_MEMORY.createEntityManagerFactory();
	}
}
