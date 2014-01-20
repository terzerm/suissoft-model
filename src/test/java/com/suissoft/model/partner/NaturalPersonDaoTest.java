package com.suissoft.model.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.EntityManagerDao;

/**
 * Unit test for {@link Partner}
 */
public class NaturalPersonDaoTest {
	
	private Dao<NaturalPerson> dao;
	private Dao<Address> daoAddress;
	private List<Long> toDelete;

	@Before
	public void beforeEach() {
		toDelete = new ArrayList<>();
		final EntityManager entityManager = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
		assertNotNull("should get entityManager", entityManager);
		dao = new EntityManagerDao<>(NaturalPerson.class, entityManager);
		daoAddress = new EntityManagerDao<>(Address.class, entityManager);
	}
	
	@After
	public void afterEach() {
		for (final long idToDelete : toDelete) {
			dao.delete(idToDelete);
		}
	}

	private NaturalPerson insert(String firstName, String lastName) {
		return insert(firstName, lastName, null);
	}
	private NaturalPerson insert(String firstName, String lastName, LocalDate birthday) {
		return insert(firstName, lastName, birthday, new NaturalPerson());
	}
	private NaturalPerson insertCreateByDao(String firstName, String lastName, LocalDate birthday) {
		return insert(firstName, lastName, birthday, dao.create());
	}
	private NaturalPerson insert(String firstName, String lastName, LocalDate birthday, NaturalPerson person) {
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setBirthday(birthday);
		dao.insertOrUpdate(person);
		return person;
	}
	
	private Address createAddress(String... lines) {
		if (lines.length > 3) throw new IndexOutOfBoundsException("at max 3 address lines are allowed, but found: " + lines.length);
		final Address addr = new Address();
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
		
		final List<NaturalPerson> allPersons = dao.findAll();
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
		dao.insertOrUpdate(p);
	}
	@Test
	public void shouldDelete() {
		final NaturalPerson p = insert("Peer", "Woodbridge");
		assertNotEquals("generated ID should be different from 0", 0, p.getId());
		assertNotNull("should find by ID", dao.findById(p.getId()));
		assertTrue("should delete by ID", dao.delete(p.getId()));
		assertNull("should no longer find by ID", dao.findById(p.getId()));
		p.setId(0);
		p.setAddresses(new ArrayList<Address>());
		dao.insertOrUpdate(p);
		assertNotNull("should find again by ID", dao.findById(p.getId()));
		dao.delete(p);
		assertNull("should no longer find after delete", dao.findById(p.getId()));
	}
	
	@Test
	public void shouldInsertWithAddress() {
		final NaturalPerson p = insert("Peer", "Woodbridge");
		p.addAddress(createAddress("Emmastrasse 14", "3000 Bern"));
		p.addAddress(createAddress("Workaholic square", "8005 Zurich"));
		dao.insertOrUpdate(p);
		assertNotEquals("generated ID should be different from 0", 0, p.getAddresses().get(0).getId());
		assertNotEquals("generated ID should be different from 0", 0, p.getAddresses().get(1).getId());
		assertNotEquals("generated ID's should be different", p.getAddresses().get(0).getId(), p.getAddresses().get(1).getId());
		assertNotNull("should have owner", p.getAddresses().get(0).getOwner());
		assertNotNull("should have owner", p.getAddresses().get(1).getOwner());
		assertNotNull("should find by ID", daoAddress.findById(p.getAddresses().get(0).getId()));
		assertNotNull("should find by ID", daoAddress.findById(p.getAddresses().get(1).getId()));

		p.getAddresses().get(0).setAddressLine3("Switzerland");
		dao.insertOrUpdate(p);
		assertNotNull("should find by ID", daoAddress.findById(p.getAddresses().get(0).getId()));
		assertEquals("should have been updated", "Switzerland", daoAddress.findById(p.getAddresses().get(0).getId()).getAddressLine3());
	}
}
