package com.suissoft.model.app.partner.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="T_VALUE_CONTACT")
public class ValueContact extends Contact {

	private String value;
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
