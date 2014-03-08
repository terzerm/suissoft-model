package com.suissoft.model.app.partner.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.Guice;
import com.suissoft.model.app.partner.dao.NaturalPersonDao;
import com.suissoft.model.entity.Entity;
import com.suissoft.model.persistence.Dao;
import com.suissoft.model.persistence.PersistenceModule;
import com.suissoft.model.persistence.PersistenceUnit;

/**
 * Unit test for {@link Dao} for {@link NaturalPerson}
 */
@RunWith(MockitoJUnitRunner.class)
public class NaturalPersonDaoTest {
	
	private final PersistenceUnit unit = PersistenceUnit.H2_FILE;
	
	@Inject
	private Dao<NaturalPerson> daoNaturalPerson;

	@Inject
	private NaturalPersonDao daoNaturalPersonExt;

	@Inject
	private Dao<Address> daoAddress;

	@Inject
	private Dao<ContactInfoType> daoContactType;
	
	@Inject
	private Dao<AddressType> daoAddressType;
	
	@Inject
	private Dao<ContactInfoType> daoContactInfoType;

	private List<Long> toDelete;

	@Before
	public void beforeEach() {
		toDelete = new ArrayList<>();
		Guice.createInjector(new PersistenceModule(unit)).injectMembers(this);
		assertNotNull("daoNaturalPerson should have been injected", daoNaturalPerson);
		assertNotNull("daoAddress should have been injected", daoAddress);
	}
	
	@After
	public void afterEach() {
		for (final long idToDelete : toDelete) {
			daoNaturalPerson.delete(idToDelete);
		}
	}

	private NaturalPerson insertNaturalPerson(String firstName, String lastName) {
		return insertNaturalPerson(firstName, lastName, null);
	}
	private NaturalPerson insertNaturalPerson(String firstName, String lastName, LocalDate birthday) {
		return insert(firstName, lastName, birthday, new NaturalPerson());
	}
	private NaturalPerson insertCreateByDao(String firstName, String lastName, LocalDate birthday) {
		return insert(firstName, lastName, birthday, daoNaturalPerson.newEntity());
	}
	private NaturalPerson insert(String firstName, String lastName, LocalDate birthday, NaturalPerson person) {
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setBirthday(birthday);
		person = daoNaturalPerson.insertOrUpdate(person);
		toDelete.add(person.getId());
		return person;
	}
	
	private Address createAddress(AddressType addressType, String... lines) {
		if (lines.length > 3) throw new IndexOutOfBoundsException("at max 3 address lines are allowed, but found: " + lines.length);
		final Address addr = new Address();
		addr.setAddressType(addressType);
		if (lines.length > 0) addr.setAddressLine1(lines[0]);
		if (lines.length > 1) addr.setAddressLine2(lines[1]);
		if (lines.length > 2) addr.setAddressLine3(lines[2]);

		return addr;
	}

	@Test
	public void shouldInsertAndFindAll() {
		final NaturalPerson p0 = insertNaturalPerson("Poor", "NoBirthayMan");
		final NaturalPerson p1 = insertNaturalPerson("Piedro", "Mullero", new LocalDate(1973, 1, 31));
		final NaturalPerson p2 = insertCreateByDao("Marta", "Bellerista", new LocalDate(1975, 4, 22));
		
		assertNotEquals("generated ID should be different from 0", 0, p0.getId());
		assertNotEquals("generated ID should be different from 0", 0, p1.getId());
		assertNotEquals("generated ID should be different from 0", 0, p2.getId());
		assertNotEquals("two inserts should generate different ID's", p0.getId(), p1.getId());
		assertNotEquals("two inserts should generate different ID's", p1.getId(), p2.getId());
		assertNotEquals("two inserts should generate different ID's", p0.getId(), p2.getId());
		
		final List<NaturalPerson> allPersons = daoNaturalPerson.findAll();
		assertTrue("at least 3 persons should exist now", 3 <= allPersons.size());
		
		assertEntityIncluded(allPersons, p0.getId(), p1.getId(), p2.getId());
	}

	@Test
	public void shouldUpdate() {
		final NaturalPerson p = insertNaturalPerson("Poor", "NoBirthayMan");
		assertNotEquals("generated ID should be different from 0", 0, p.getId());
		p.setLastName("HappyBirthddayMan");
		p.setBirthday(new LocalDate(1956, 12, 22));
		daoNaturalPerson.insertOrUpdate(p);
	}
	@Test
	public void shouldDelete() {
		NaturalPerson p = insertNaturalPerson("Peer", "Woodbridge");
		assertNotEquals("generated ID should be different from 0", 0, p.getId());
		assertNotNull("should find by ID", daoNaturalPerson.findById(p.getId()));
		assertTrue("should delete by ID", daoNaturalPerson.delete(p.getId()));
		assertNull("should no longer find by ID", daoNaturalPerson.findById(p.getId()));
		p.setId(0);
//TODO check if should be deleted		p.setContacts(new ArrayList<Contact>());
		p = daoNaturalPerson.insertOrUpdate(p);
		assertNotNull("should find again by ID", daoNaturalPerson.findById(p.getId()));
		daoNaturalPerson.delete(p);
		assertNull("should no longer find after delete", daoNaturalPerson.findById(p.getId()));
	}
	
	@Test
	public void shouldInsertWithContactInfo() {
		ContactInfoType phoneType = getCreateContactInfoType();

		NaturalPerson p = insertNaturalPerson("Friedrich", "Nietzsche");
		p.addContactInfo(createContactInfo(phoneType, "0800 666"));
		p.addContactInfo(new ContactInfo());
		p = daoNaturalPerson.insertOrUpdate(p);
	}
	
	@Test
	public void shouldInsertWithAddress() {
		AddressType addressType = getCreateAddressType();

		NaturalPerson p = insertNaturalPerson("Peer", "Woodbridge");
		p.addAddress(createAddress(addressType, "Emmastrasse 14", "3000 Bern"));
		p.addAddress(createAddress(addressType, "Workaholic square", "8005 Zurich"));
		p = daoNaturalPerson.insertOrUpdate(p);
		assertNotEquals("generated ID should be different from 0", 0, p.getAddresses().get(0).getId());
		assertNotEquals("generated ID should be different from 0", 0, p.getAddresses().get(1).getId());
		assertNotEquals("generated ID's should be different", p.getAddresses().get(0).getId(), p.getAddresses().get(1).getId());
		assertNotNull("should have owner", p.getAddresses().get(0).getOwner());
		assertNotNull("should have owner", p.getAddresses().get(1).getOwner());
		assertNotNull("should find by ID", daoAddress.findById(p.getAddresses().get(0).getId()));
		assertNotNull("should find by ID", daoAddress.findById(p.getAddresses().get(1).getId()));

		((Address) p.getAddresses().get(0)).setAddressLine3("Switzerland");
		p = daoNaturalPerson.insertOrUpdate(p);
		assertNotNull("should find by ID", daoAddress.findById(p.getAddresses().get(0).getId()));
		assertEquals("should have been updated", "Switzerland", daoAddress.findById(p.getAddresses().get(0).getId()).getAddressLine3());
	}
	
	private AddressType getCreateAddressType() {
		List<AddressType> existingAddressTypes = daoAddressType.findAll();
		if (existingAddressTypes.isEmpty()) {
			AddressType newAddressType = new AddressType();
			newAddressType.setName("Friend");
			daoAddressType.insertOrUpdate(newAddressType);
			existingAddressTypes = daoAddressType.findAll();
		}
		return existingAddressTypes.get(0);
	}

	private ContactInfoType getCreateContactInfoType() {
		List<ContactInfoType> existingContactInfoTypes = daoContactInfoType.findAll();
		if (existingContactInfoTypes.isEmpty()) {
			ContactInfoType newContactInfoType = new ContactInfoType();
			newContactInfoType.setName("Snailmail");
			daoContactInfoType.insertOrUpdate(newContactInfoType);
			existingContactInfoTypes = daoContactInfoType.findAll();
		}
		return existingContactInfoTypes.get(0);
	}

	private ContactInfo createContactInfo(ContactInfoType contactInfoType, String value) {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setContactInfoType(contactInfoType);
		contactInfo.setValue(value);
		return contactInfo;
	}
	
	@Test
	public void shouldInsertAndFindByName() {
		final String lastName0 = "Mad Man " + UUID.randomUUID();
		final String lastName1 = "Billabong " + UUID.randomUUID();
		final NaturalPerson p0 = insertNaturalPerson("Henry", lastName0);
		final NaturalPerson p1 = insertNaturalPerson("Piedro", lastName1, new LocalDate(1973, 1, 31));
		
		final List<NaturalPerson> allPersons = daoNaturalPerson.findAll();
		assertEntityIncluded(allPersons, p0.getId(), p1.getId());
		
		final List<NaturalPerson> find0 = daoNaturalPersonExt.findByLastName(lastName0);
		final List<NaturalPerson> find1 = daoNaturalPersonExt.findByLastName(lastName1);
		final List<NaturalPerson> findNothing = daoNaturalPersonExt.findByLastName("blablabla deflkajldkjfaldk DEFINITELY NOT FOUND" + UUID.randomUUID());
		
		assertEquals("find0 should contain one entry", 1, find0.size());
		assertEquals("find1 should contain one entry", 1, find1.size());
		assertTrue("findNothing should be empty", findNothing.isEmpty());

		assertEntityIncluded(find0, p0.getId());
		assertEntityIncluded(find1, p1.getId());

		final List<NaturalPerson> anotherFind0 = daoNaturalPersonExt.findByFirstAndLastName("Henry", lastName0);
		final List<NaturalPerson> anotherFind1 = daoNaturalPersonExt.findByFirstAndLastName("Piedro", lastName1);
		final List<NaturalPerson> anotherFindNothing = daoNaturalPersonExt.findByFirstAndLastName("Piedro", lastName0);
		
		assertEntityIncluded(anotherFind0, p0.getId());
		assertEntityIncluded(anotherFind1, p1.getId());
		assertTrue("findNothing should be empty", anotherFindNothing.isEmpty());

		assertEntityIncluded(anotherFind0, p0.getId());
		assertEntityIncluded(anotherFind1, p1.getId());
	}
	
	@Test
	public void shouldFindOnePersonByOneSearchTerm() {
		StringBuilder sb = new StringBuilder();
		for (NaturalPerson np: daoNaturalPerson.findAll()) {
			sb.append("P1: " + np.toString() + "\n");
		}
		for (NaturalPerson np: daoNaturalPersonExt.findAll()) {
			sb.append("P2: " + np.toString() + "\n");
		}
		System.out.println(sb.toString());
		NaturalPerson einstein = insertNaturalPerson("Albert", "Einstein");
		NaturalPerson newton = insertNaturalPerson("Isaac", "Newton");
		
		List<NaturalPerson> resultEinstein = daoNaturalPersonExt.findBySearchTerms(buildSearchTerms("bert"));
		assertEquals(1, resultEinstein.size());
		assertTrue(resultEinstein.get(0).getId() == einstein.getId());
		assertFalse(resultEinstein.get(0).getId() == newton.getId());
	}
	
	@Test
	public void shouldFindBothByTwoSearchTerms() {
		NaturalPerson einstein = insertNaturalPerson("Albert", "Einstein");
		NaturalPerson newton = insertNaturalPerson("Isaac", "Newton");
		
		List<NaturalPerson> resultBoth = daoNaturalPersonExt.findBySearchTerms(buildSearchTerms("a"));
		assertEquals(2, resultBoth.size());
		assertEntityIncluded(resultBoth, einstein.getId(), newton.getId());
	}

	@Test
	public void shouldFindNoneByTwoSearchTerms() {
		NaturalPerson einstein = insertNaturalPerson("Albert", "Einstein");
		NaturalPerson newton = insertNaturalPerson("Isaac", "Newton");
		
		List<NaturalPerson> resultNone = daoNaturalPersonExt.findBySearchTerms(buildSearchTerms("hello", "world"));
		assertEquals(0, resultNone.size());
		assertEntityNotIncluded(resultNone, einstein.getId(), newton.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailToSearchByZeroSearchTerms() {
		daoNaturalPersonExt.findBySearchTerms(buildSearchTerms());
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldFailToSearchByNullSearchTerms() {
		daoNaturalPersonExt.findBySearchTerms(null);
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
