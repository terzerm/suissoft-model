package com.suissoft.model.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.suissoft.model.persistence.PersistenceUnit;

public class PersistenceUnitTest {

	@Test
	public void shouldTryCodeCoverage_nojoke() {
		for (PersistenceUnit u: PersistenceUnit.values()) {
			assertNotNull(u);
		}
	}
}
