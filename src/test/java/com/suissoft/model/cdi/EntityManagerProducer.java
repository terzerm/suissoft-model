package com.suissoft.model.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.suissoft.model.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {
	@Produces
	@ApplicationScoped
	public EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(PersistenceUnit.H2_MEMORY.name());
	}
}
