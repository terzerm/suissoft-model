package com.suissoft.model.partner;

import java.util.List;

public class Partner {
	private long id;
	private List<Address> addresses;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
}
