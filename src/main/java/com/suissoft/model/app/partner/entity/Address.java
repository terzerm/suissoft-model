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
import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_ADDRESS")
public class Address extends AbstractEntity {

	private Partner owner;

	private AddressType addressType;

	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String zip;
	private String town;
	private String region;
	private Country country;
	private String longitude;
	private String latitude;
	
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
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@ManyToOne(optional=true)
	public AddressType getAddressType() {
		return addressType;
	}
	
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitAddress(this, input);
	}
	
}
