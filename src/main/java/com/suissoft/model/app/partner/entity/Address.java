package com.suissoft.model.app.partner.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;

@Entity
@Table(name="T_ADDRESS")
public class Address extends AbstractEntity {
	private Partner owner;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	
	private Country country;
	
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
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	
	@ManyToOne
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
