package com.suissoft.model.partner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.suissoft.model.AbstractEntity;

@Entity
@Table(name="T_PARTNER")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Partner extends AbstractEntity {

	private List<Address> addresses = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="owner", orphanRemoval=true)
	@OrderColumn
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
	
	abstract public <I,R> R accept(PartnerVisitor<I,R> visitor, I input);
}
