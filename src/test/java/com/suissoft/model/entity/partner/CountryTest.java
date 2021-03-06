package com.suissoft.model.entity.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.partner.Country;

public class CountryTest {

	private Country country;
	
	@Before
	public void setup() {
		country = new Country();
	}
	
	@Test
	public void testAbbreviationGetterAndSetter() {
		String abbreviation = "DE";
		country.setAbbreviation(abbreviation);
		assertEquals(abbreviation, country.getAbbreviation());
	}
	
	@Test
	public void testNameGetterAndSetter() {
		String name = "Schland";
		country.setName(name);
		assertEquals(name, country.getName());
	}
}
