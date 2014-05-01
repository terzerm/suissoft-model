package com.suissoft.model.entity.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_ADDRESS_TYPE")
public class AddressType extends AbstractEntity {

	private String name;

	private String accessCode;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length = 64, nullable=true)
	public String getAccessCode() {
		return accessCode;
	}
	
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitAddressType(this, input);
	}
}
