package com.suissoft.model.app.partner.entity.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.suissoft.model.app.partner.entity.AddressType;

public class AddressTypeTest {

	@Test
	public void testGettersAndSetters() {
		AddressType at = new AddressType();
		
		at.setAccessCode("hello");
		assertEquals("hello", at.getAccessCode());
		
		at.setName("delivery");
		assertEquals("delivery",  at.getName());
	}
}
