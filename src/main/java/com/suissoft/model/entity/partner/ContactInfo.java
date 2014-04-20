package com.suissoft.model.entity.partner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_CONTACT_INFO")
public class ContactInfo extends AbstractEntity {

	private Partner owner;

	private ContactInfoType contactInfoType;
	private String value;

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true)
	public Partner getOwner() {
		return owner;
	}

	public void setOwner(Partner owner) {
		this.owner = owner;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne(optional=true)
	public ContactInfoType getContactInfoType() {
		return contactInfoType;
	}

	public void setContactInfoType(ContactInfoType contactInfoType) {
		this.contactInfoType = contactInfoType;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitContatInfo(this, input);
	}
}
