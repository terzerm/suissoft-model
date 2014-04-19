package com.suissoft.model.app.partner.dao;

import static com.suissoft.model.util.PersistenceUnit.H2_MEMORY;

import com.google.inject.Guice;
import com.suissoft.model.guice.EntityManagerModule;

public class JuristicPersonDaoGuiceTest extends AbstractJuristicPersonDaoTest {

	public JuristicPersonDaoGuiceTest() {
		Guice.createInjector(new EntityManagerModule(H2_MEMORY)).injectMembers(this);
	}
}
