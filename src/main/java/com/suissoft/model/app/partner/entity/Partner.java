package com.suissoft.model.app.partner.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.suissoft.model.app.partner.PartnerVisitor;
import com.suissoft.model.entity.AbstractEntity;

@Entity
@Table(name="T_PARTNER")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Partner extends AbstractEntity {

	private List<Contact> contacts = new ArrayList<>();
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="owner", orphanRemoval=true)
	@OrderColumn
	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(Contact contact) {
		contact.setOwner(this);
		contacts.add(contact);
	}
	
	abstract public <I,R> R accept(PartnerVisitor<I,R> visitor, I input);
}
