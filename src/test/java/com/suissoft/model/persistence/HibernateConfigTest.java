package com.suissoft.model.persistence;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.lang.management.ManagementFactory;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import com.suissoft.model.util.PersistenceUnit;

/**
 * Unit test verifying the hibernate config.
 */
public class HibernateConfigTest {

	@BeforeClass
	public static void beforeClass() {
		/**RuntimeMXBean rt =*/ ManagementFactory.getRuntimeMXBean();
	}
	
	@Test
	public void shouldFindPersistenceXml() {
		final InputStream in = getClass().getResourceAsStream("/META-INF/persistence.xml");
		assertNotNull("persistence.xml input stream should not be null", in);
	}

	@Test
	public void shouldGetEntityManager() {
		for (final PersistenceUnit persistenceUnit : PersistenceUnit.values()) {
			final EntityManager entityManager = javax.persistence.Persistence.createEntityManagerFactory(persistenceUnit.name()).createEntityManager();
			assertNotNull("should get entityManager", entityManager);
			entityManager.close();
		}
	}
}
