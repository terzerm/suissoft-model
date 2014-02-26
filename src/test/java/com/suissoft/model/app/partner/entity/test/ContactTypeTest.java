package com.suissoft.model.app.partner.entity.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.app.partner.entity.ContactType;

public class ContactTypeTest {

	private ContactType contactType;
	
	@Before
	public void setup() {
		contactType = new ContactType();
	}
	
	@Test
	public void testNameGetterAndSetter() {
		String name = "friend";
		contactType.setName(name);
		assertEquals(name, contactType.getName());
	}
}
