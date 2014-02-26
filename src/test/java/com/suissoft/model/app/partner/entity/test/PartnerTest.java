package com.suissoft.model.app.partner.entity.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.suissoft.model.app.partner.entity.JuristicPerson;
import com.suissoft.model.app.partner.entity.NaturalPerson;
import com.suissoft.model.app.partner.entity.Partner;
import com.suissoft.model.persistence.test.AbstractPersistenceUnitTest;

/**
 * Unit test for {@link Partner}
 */
public class PartnerTest extends AbstractPersistenceUnitTest {
	
	private NaturalPerson insertNaturalPerson(String firstName, String lastName, LocalDate birthday) {
		final NaturalPerson p = new NaturalPerson();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setBirthday(birthday);
		entityManager.persist(p);
		entitiesToDelete.add(p);
		return p;
	}

	private JuristicPerson insertJuristicPerson(String name) {
		final JuristicPerson p = new JuristicPerson();
		p.setName(name);
		entityManager.persist(p);
		entitiesToDelete.add(p);
		return p;
	}

	@Test
	public void shouldInsertNaturalPerson() {
		entityManager.getTransaction().begin();
		final NaturalPerson p1 = insertNaturalPerson("Piedro", "Mullero", new LocalDate(1973, 1, 31));
		final NaturalPerson p2 = insertNaturalPerson("Marta", "Bellerista", new LocalDate(1975, 4, 22));

		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.getTransaction().commit();
		
		assertNotEquals("generated ID should be different from 0", 0, p1.getId());
		assertNotEquals("generated ID should be different from 0", 0, p2.getId());
		
		entityManager.contains(p1);
		entityManager.contains(p2);
		assertNotEquals("two inserts should generate different ID's", p1.getId(), p2.getId());

		assertNotNull("address list should not be null", p1.getContacts());
		assertNotNull("address list should not be null", p2.getContacts());
	}

	@Test
	public void shouldInsertJuristicPerson() {
		entityManager.getTransaction().begin();
		final JuristicPerson p1 = insertJuristicPerson("Microsoft");
		final JuristicPerson p2 = insertJuristicPerson("Apple");

		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.getTransaction().commit();
		
		assertNotEquals("generated ID should be different from 0", 0, p1.getId());
		assertNotEquals("generated ID should be different from 0", 0, p2.getId());
		
		entityManager.contains(p1);
		entityManager.contains(p2);
		assertNotEquals("two inserts should generate different ID's", p1.getId(), p2.getId());

		assertNotNull("address list should not be null", p1.getContacts());
		assertNotNull("address list should not be null", p2.getContacts());
	}
}
