package com.suissoft.model.app.partner.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.suissoft.model.app.partner.entity.ContactInfo;
import com.suissoft.model.app.partner.entity.ContactInfoType;
import com.suissoft.model.app.partner.entity.NaturalPerson;
import com.suissoft.model.app.partner.entity.Partner;

public class ContactInfoTest {

	
	@Test
	public void testGettersAndSetters() {
		ContactInfo contactInfo = new ContactInfo();
		
		ContactInfoType contactInfoType = new ContactInfoType();
		contactInfo.setContactInfoType(contactInfoType);
		assertEquals(contactInfoType, contactInfo.getContactInfoType());
		
		Partner owner = new NaturalPerson();
		contactInfo.setOwner(owner);
		assertEquals(owner, contactInfo.getOwner());
	}
}
