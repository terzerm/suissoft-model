package com.suissoft.model;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.suissoft.persistence.PersistenceUnit;

/**
 * Unit test verifying the hibernate config.
 */
public class HibernateConfigTest {

	@BeforeClass
	public static void beforeClass() {
		RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
		System.out.println("CLASSPATH: " + rt.getClassPath());
	}
	
	@Test
	public void shouldFindPersistenceXml() {
		final InputStream in = getClass().getResourceAsStream("/META-INF/persistence.xml");
		assertNotNull("persistence.xml input stream should not be null", in);
	}

	@Test
	public void shouldGetEntityManager() {
		for (final PersistenceUnit persistenceUnit : PersistenceUnit.values()) {
			final EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceUnit.name()).createEntityManager();
			assertNotNull("should get entityManager", entityManager);
			entityManager.close();
		}
	}
}
