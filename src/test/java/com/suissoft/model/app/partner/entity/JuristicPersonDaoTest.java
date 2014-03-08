package com.suissoft.model.app.partner.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.suissoft.model.app.partner.dao.JuristicPersonDao;
import com.suissoft.model.entity.Entity;
import com.suissoft.model.persistence.Dao;
import com.suissoft.model.persistence.PersistenceModule;
import com.suissoft.model.persistence.PersistenceUnit;

public class JuristicPersonDaoTest {

	private final PersistenceUnit unit = PersistenceUnit.H2_FILE;
	
	@Inject
	private Dao<JuristicPerson> daoJuristicPerson;

	@Inject
	private JuristicPersonDao daoJuristicPersonExt;

	@Inject
	private Dao<Address> daoAddress;

	@Inject
	private Dao<ContactInfoType> daoContactType;
	
	private List<Long> toDelete;

	@Before
	public void beforeEach() {
		toDelete = new ArrayList<>();
		Guice.createInjector(new PersistenceModule(unit)).injectMembers(this);
		assertNotNull("daoJuristicPerson should have been injected", daoJuristicPerson);
		assertNotNull("daoAddress should have been injected", daoAddress);

		for (JuristicPerson juristicPerson: daoJuristicPerson.findAll()) {
			daoJuristicPerson.delete(juristicPerson);
		}
}
	
	@After
	public void afterEach() {
		for (final long idToDelete : toDelete) {
			daoJuristicPerson.delete(idToDelete);
		}
	}

	@Test
	public void shouldFindOneCompanyByOneSearchTerm() {
		JuristicPerson acme = insert("ACME Inc");
		JuristicPerson cocaCola = insert("Coca cola");
		
		List<JuristicPerson> resultAcme = daoJuristicPersonExt.findBySearchTerms(buildSearchTerms("inc"));
		assertEquals(1, resultAcme.size());
		assertEntityIncluded(resultAcme, acme.getId());
		assertEntityNotIncluded(resultAcme, cocaCola.getId());
	}
	
	@Test
	public void shouldFindBothByTwoSearchTerms() {
		JuristicPerson acme = insert("ACME Inc");
		JuristicPerson cocaCola = insert("Coca cola");
		
		List<JuristicPerson> resultBoth = daoJuristicPersonExt.findBySearchTerms(buildSearchTerms("a"));
		assertEquals(2, resultBoth.size());
		assertEntityIncluded(resultBoth, acme.getId(), cocaCola.getId());
	}

	@Test
	public void shouldFindNoneByTwoSearchTerms() {
		JuristicPerson acme = insert("ACME Inc");
		JuristicPerson cocaCola = insert("Coca cola");
		
		List<JuristicPerson> resultNone = daoJuristicPersonExt.findBySearchTerms(buildSearchTerms("hello", "world"));
		assertEquals(0, resultNone.size());
		assertFalse(resultNone.contains(acme));
		assertFalse(resultNone.contains(cocaCola));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailToSearchByZeroSearchTerms() {
		daoJuristicPersonExt.findBySearchTerms(buildSearchTerms());
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldFailToSearchByNullSearchTerms() {
		daoJuristicPersonExt.findBySearchTerms(null);
	}
	
	@Test
	public void shouldInjectContactTypeDao() {
		assertNotNull("daoContactType should not be null", daoContactType);
	}

	private List<String> buildSearchTerms(String ...parts) {
		List<String> result = new ArrayList<>();
		for (String s: parts) {
			result.add(s);
		}
		return result;
	}

	private JuristicPerson insert(String name) {
		JuristicPerson company = daoJuristicPerson.newEntity();
		company.setName(name);
		company = daoJuristicPerson.insertOrUpdate(company);
		toDelete.add(company.getId());
		return company;
	}

	private void assertEntityIncluded(List<? extends Entity> entities, Long...ids) {
		for (long id: ids) {
			boolean found = false;
			for (Entity entity: entities) {
				if (entity.getId() == id) {
					found = true;
				}
			}
			assertTrue(found);
		}
	}

	private void assertEntityNotIncluded(List<? extends Entity> entities, Long...ids) {
		for (long id: ids) {
			boolean found = false;
			for (Entity entity: entities) {
				if (entity.getId() == id) {
					found = true;
				}
			}
			assertFalse(found);
		}
	}

}
