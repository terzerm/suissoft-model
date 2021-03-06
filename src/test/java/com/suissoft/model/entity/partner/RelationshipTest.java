package com.suissoft.model.entity.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.partner.JuristicPerson;
import com.suissoft.model.entity.partner.NaturalPerson;
import com.suissoft.model.entity.partner.Relationship;
import com.suissoft.model.entity.partner.RelationshipType;

public class RelationshipTest {

	private Relationship relationship;

	@Before
	public void setup() {
		relationship = new Relationship();
	}

	@Test
	public void testRelationshipTypeGetterAndSetter() {
		RelationshipType relationshipType = new RelationshipType();
		relationship.setRelationshipType(relationshipType);
		assertEquals(relationshipType, relationship.getRelationshipType());
	}

	@Test
	public void testPartnerGettersAndSetters() {
		NaturalPerson partnerFrom = new NaturalPerson();
		JuristicPerson partnerTo = new JuristicPerson();
		
		relationship.setPartnerFrom(partnerFrom);
		relationship.setPartnerTo(partnerTo);
		
		assertEquals(partnerFrom, relationship.getPartnerFrom());
		assertEquals(partnerTo, relationship.getPartnerTo());
	}
}
