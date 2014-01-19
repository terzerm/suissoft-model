package com.suissoft.model;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.suissoft.persistence.unit.Persistence;


/**
 * Base class for parameterized tests looping over all {@link Persistence} entries.
 */
@RunWith(Parameterized.class)
abstract public class AbstractPersistenceUnitTest {
	
	@Parameter
	public Persistence.Unit peristenceUnit;
	
	protected EntityManager entityManager;
	protected List<Object> entitiesToDelete;
	
	@Parameters
	public static List<Object[]> getParameters() {
		final List<Object[]> params = new ArrayList<>();
		for (final Persistence.Unit persistenceUnit : Persistence.Unit.values()) {
			params.add(new Object[]{persistenceUnit});
		}
		return params;
	}

	@Before
	public void beforeEach() {
		entitiesToDelete = new ArrayList<>();
		entityManager = javax.persistence.Persistence.createEntityManagerFactory(peristenceUnit.name()).createEntityManager();
		assertNotNull("should get entityManager", entityManager);
	}
	
	@After
	public void afterEach() {
		for (final Object toDelete : entitiesToDelete) {
			try {
				entityManager.getTransaction().begin();
				entityManager.remove(toDelete);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				System.err.println("ERR: delete failed for entity: " + toDelete);
				e.printStackTrace();
			}
		}
		entityManager.clear();
		entityManager.close();
	}
}
