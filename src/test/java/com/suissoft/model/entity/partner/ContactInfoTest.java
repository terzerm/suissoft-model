package com.suissoft.model.entity.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.suissoft.model.entity.partner.ContactInfo;
import com.suissoft.model.entity.partner.ContactInfoType;
import com.suissoft.model.entity.partner.NaturalPerson;
import com.suissoft.model.entity.partner.Partner;

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
