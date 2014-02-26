package com.suissoft.model.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.suissoft.model.app.partner.entity.NaturalPerson;

public class AbstractEntityTest {

	@Test
	public void testHashCodeAndEquals() {
		NaturalPerson john1 = new NaturalPerson();
		john1.setFirstName("John");
		NaturalPerson john2 = new NaturalPerson();
		john2.setFirstName("John");
		
		NaturalPerson jack = new NaturalPerson();
		jack.setFirstName("Jack");
		
		assertEquals(john1, john2);
		assertEquals(john1.hashCode(), john2.hashCode());
		
		assertNotEquals(john1, jack);
		assertNotEquals(john1.hashCode(), jack.hashCode());
	}
	
	@Test
	public void testToString() {
		NaturalPerson john = new NaturalPerson();
		john.setFirstName("John");
		john.setLastName("Doe");
		String toString = john.toString();
		assertTrue(toString.contains("John"));
	}
}
