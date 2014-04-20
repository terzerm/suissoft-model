package com.suissoft.model.entity.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.partner.ContactInfo;

public class ValueContactTest {

	private ContactInfo contactInfo;
	
	@Before
	public void setup() {
		contactInfo = new ContactInfo();
	}
	
	@Test
	public void shouldTestValueGetterAndSetter() {
		String value = "TheTestValue";
		contactInfo.setValue(value);
		assertEquals(value, contactInfo.getValue());
	}
}
