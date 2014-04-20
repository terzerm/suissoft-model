package com.suissoft.model.entity.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.suissoft.model.entity.partner.AddressType;

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
