package com.suissoft.model.app.partner.entity.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.app.partner.entity.ValueContact;

public class ValueContactTest {

	private ValueContact valueContact;
	
	@Before
	public void setup() {
		valueContact = new ValueContact();
	}
	
	@Test
	public void shouldTestValueGetterAndSetter() {
		String value = "TheTestValue";
		valueContact.setValue(value);
		assertEquals(value, valueContact.getValue());
	}
}
