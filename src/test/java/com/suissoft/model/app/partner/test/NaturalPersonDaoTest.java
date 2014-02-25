package com.suissoft.model.app.partner.test;

import static org.junit.Assert.assertEquals;
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

import com.google.inject.Guice;
import com.suissoft.model.app.partner.dao.NaturalPersonDao;
import com.suissoft.model.app.partner.entity.Address;
import com.suissoft.model.app.partner.entity.Contact;
import com.suissoft.model.app.partner.entity.ContactType;
import com.suissoft.model.app.partner.entity.NaturalPerson;
import com.suissoft.model.persistence.Dao;
import com.suissoft.model.persistence.PersistenceModule;
import com.suissoft.model.persistence.PersistenceUnit;

/**
 * Unit test for {@link Dao} for {@link NaturalPerson}
 */
public class NaturalPersonDaoTest {
	
	private final PersistenceUnit unit = PersistenceUnit.H2_FILE;
	
	@Inject
	private Dao<NaturalPerson> daoNaturalPerson;

	@Inject
	private NaturalPersonDao daoNaturalPersonExt;

	@Inject
	private Dao<Address> daoAddress;

	@Inject
	private Dao<ContactType> daoContactType;
	
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

	private NaturalPerson insert(String firstName, String lastName) {
		return insert(firstName, lastName, null);
	}
	private NaturalPerson insert(String firstName, String lastName, LocalDate birthday) {
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
	
	private Contact createAddressContact(ContactType contactType, String... lines) {
		if (lines.length > 3) throw new IndexOutOfBoundsException("at max 3 address lines are allowed, but found: " + lines.length);
		final Address addr = new Address();
		addr.setContactType(contactType);
		if (lines.length > 0) addr.setAddressLine1(lines[0]);
		if (lines.length > 1) addr.setAddressLine2(lines[1]);
		if (lines.length > 2) addr.setAddressLine3(lines[2]);

		return addr;
	}

	@Test
	public void shouldInsertAndFindAll() {
		final NaturalPerson p0 = insert("Poor", "NoBirthayMan");
		final NaturalPerson p1 = insert("Piedro", "Mullero", new LocalDate(1973, 1, 31));
		final NaturalPerson p2 = insertCreateByDao("Marta", "Bellerista", new LocalDate(1975, 4, 22));
		
		assertNotEquals("generated ID should be different from 0", 0, p0.getId());
		assertNotEquals("generated ID should be different from 0", 0, p1.getId());
		assertNotEquals("generated ID should be different from 0", 0, p2.getId());
		assertNotEquals("two inserts should generate different ID's", p0.getId(), p1.getId());
		assertNotEquals("two inserts should generate different ID's", p1.getId(), p2.getId());
		assertNotEquals("two inserts should generate different ID's", p0.getId(), p2.getId());
		
		final List<NaturalPerson> allPersons = daoNaturalPerson.findAll();
		assertTrue("at least 3 persons should exist now", 3 <= allPersons.size());
		
		final int index0 = allPersons.indexOf(p0);
		final int index1 = allPersons.indexOf(p1);
		final int index2 = allPersons.indexOf(p2);
		assertTrue("person0 should be in list", index0 >= 0);
		assertTrue("person1 should be in list", index1 >= 0);
		assertTrue("person2 should be in list", index2 >= 0);
		assertNotEquals("person0 should different from person1", index0, index1);
		assertNotEquals("person1 should different from person2", index1, index2);
		assertNotEquals("person0 should different from person2", index0, index2);
	}

	@Test
	public void shouldUpdate() {
		final NaturalPerson p = insert("Poor", "NoBirthayMan");
		assertNotEquals("generated ID should be different from 0", 0, p.getId());
		p.setLastName("HappyBirthddayMan");
		p.setBirthday(new LocalDate(1956, 12, 22));
		daoNaturalPerson.insertOrUpdate(p);
	}
	@Test
	public void shouldDelete() {
		NaturalPerson p = insert("Peer", "Woodbridge");
		assertNotEquals("generated ID should be different from 0", 0, p.getId());
		assertNotNull("should find by ID", daoNaturalPerson.findById(p.getId()));
		assertTrue("should delete by ID", daoNaturalPerson.delete(p.getId()));
		assertNull("should no longer find by ID", daoNaturalPerson.findById(p.getId()));
		p.setId(0);
		p.setContacts(new ArrayList<Contact>());
		p = daoNaturalPerson.insertOrUpdate(p);
		assertNotNull("should find again by ID", daoNaturalPerson.findById(p.getId()));
		daoNaturalPerson.delete(p);
		assertNull("should no longer find after delete", daoNaturalPerson.findById(p.getId()));
	}
	
	@Test
	public void shouldInsertWithAddress() {
		ContactType contactType = getCreateContactType();
		NaturalPerson p = insert("Peer", "Woodbridge");
		p.addContact(createAddressContact(contactType, "Emmastrasse 14", "3000 Bern"));
		p.addContact(createAddressContact(contactType, "Workaholic square", "8005 Zurich"));
		p = daoNaturalPerson.insertOrUpdate(p);
		assertNotEquals("generated ID should be different from 0", 0, p.getContacts().get(0).getId());
		assertNotEquals("generated ID should be different from 0", 0, p.getContacts().get(1).getId());
		assertNotEquals("generated ID's should be different", p.getContacts().get(0).getId(), p.getContacts().get(1).getId());
		assertNotNull("should have owner", p.getContacts().get(0).getOwner());
		assertNotNull("should have owner", p.getContacts().get(1).getOwner());
		assertNotNull("should find by ID", daoAddress.findById(p.getContacts().get(0).getId()));
		assertNotNull("should find by ID", daoAddress.findById(p.getContacts().get(1).getId()));

		((Address) p.getContacts().get(0)).setAddressLine3("Switzerland");
		p = daoNaturalPerson.insertOrUpdate(p);
		assertNotNull("should find by ID", daoAddress.findById(p.getContacts().get(0).getId()));
		assertEquals("should have been updated", "Switzerland", daoAddress.findById(p.getContacts().get(0).getId()).getAddressLine3());
	}
	
	private ContactType getCreateContactType() {
		List<ContactType> existingContactTypes = daoContactType.findAll();
		if (existingContactTypes.isEmpty()) {
			ContactType newContactType = new ContactType();
			newContactType.setName("Friend");
			daoContactType.insertOrUpdate(newContactType);
			existingContactTypes = daoContactType.findAll();
		}
		return existingContactTypes.get(0);
	}
	
	@Test
	public void shouldInsertAndFindByName() {
		final String lastName0 = "Mad Man " + UUID.randomUUID();
		final String lastName1 = "Billabong " + UUID.randomUUID();
		final NaturalPerson p0 = insert("Henry", lastName0);
		final NaturalPerson p1 = insert("Piedro", lastName1, new LocalDate(1973, 1, 31));
		
		final List<NaturalPerson> allPersons = daoNaturalPerson.findAll();
		final int index0 = allPersons.indexOf(p0);
		final int index1 = allPersons.indexOf(p1);
		assertTrue("person0 should be in list", index0 >= 0);
		assertTrue("person1 should be in list", index1 >= 0);
		
		final List<NaturalPerson> find0 = daoNaturalPersonExt.findByLastName(lastName0);
		final List<NaturalPerson> find1 = daoNaturalPersonExt.findByLastName(lastName1);
		final List<NaturalPerson> findNothing = daoNaturalPersonExt.findByLastName("blablabla deflkajldkjfaldk DEFINITELY NOT FOUND" + UUID.randomUUID());
		
		assertEquals("find0 should contain one entry", 1, find0.size());
		assertEquals("find1 should contain one entry", 1, find1.size());
		assertTrue("findNothing should be empty", findNothing.isEmpty());

		assertEquals("p0 should be in find0", p0, find0.get(0));
		assertEquals("p1 should be in find1", p1, find1.get(0));

		final List<NaturalPerson> anotherFind0 = daoNaturalPersonExt.findByFirstAndLastName("Henry", lastName0);
		final List<NaturalPerson> anotherFind1 = daoNaturalPersonExt.findByFirstAndLastName("Piedro", lastName1);
		final List<NaturalPerson> anotherFindNothing = daoNaturalPersonExt.findByFirstAndLastName("Piedro", lastName0);
		
		assertEquals("anotherFind0 should contain one entry", 1, anotherFind0.size());
		assertEquals("anotherFind1 should contain one entry", 1, anotherFind1.size());
		assertTrue("findNothing should be empty", anotherFindNothing.isEmpty());

		assertEquals("p0 should be in anotherFind0", p0, anotherFind0.get(0));
		assertEquals("p1 should be in anotherFind1", p1, anotherFind1.get(0));
	}
}
