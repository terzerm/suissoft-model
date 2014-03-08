package com.suissoft.model.app.partner.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.app.partner.entity.RelationshipType;

public class RelationshipTypeTest {

	private RelationshipType relationshipType;
	
	@Before
	public void setup() {
		relationshipType = new RelationshipType();
	}
	
	@Test
	public void testNameGetterAndSetter() {
		String name = "friend";
		relationshipType.setName(name);
		assertEquals(name, relationshipType.getName());
	}
}
