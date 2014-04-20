package com.suissoft.model.entity.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.partner.ContactInfoType;

public class ContactTypeTest {

	private ContactInfoType contactInfoType;
	
	@Before
	public void setup() {
		contactInfoType = new ContactInfoType();
	}
	
	@Test
	public void testNameGetterAndSetter() {
		String name = "friend";
		contactInfoType.setName(name);
		assertEquals(name, contactInfoType.getName());
	}

	@Test
	public void testAccessCodeGetterAndSetter() {
		String code = "CT-03";
		contactInfoType.setAccessCode(code);
		assertEquals(code, contactInfoType.getAccessCode());
	}
}
