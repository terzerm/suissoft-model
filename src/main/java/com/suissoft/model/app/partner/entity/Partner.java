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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.suissoft.model.entity.AbstractEntity;

@Entity
@Table(name="T_PARTNER")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Partner extends AbstractEntity {

	private List<ContactInfo> contactInfos = new ArrayList<>();
	private List<Address> addresses = new ArrayList<>();
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="owner", orphanRemoval=true)
	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}
	
	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}
	
	public void addContactInfo(ContactInfo contactInfo) {
		contactInfo.setOwner(this);
		contactInfos.add(contactInfo);
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="owner", orphanRemoval=true)
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address) {
		address.setOwner(this);
		addresses.add(address);
	}
	
	@Transient
	abstract public Client getClient();
	@Transient
	abstract public void setClient(Client client);

}
