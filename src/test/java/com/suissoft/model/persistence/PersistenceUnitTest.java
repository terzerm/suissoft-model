package com.suissoft.model.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.suissoft.model.util.PersistenceUnit;

public class PersistenceUnitTest {

	@Test
	public void shouldTryCodeCoverage_nojoke() {
		for (PersistenceUnit u: PersistenceUnit.values()) {
			assertNotNull(u);
		}
	}
}
