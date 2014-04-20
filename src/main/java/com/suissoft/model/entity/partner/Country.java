package com.suissoft.model.entity.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_COUNTRY")
public class Country extends AbstractEntity {
	
	private String abbreviation;
	private String name;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	/**
	 * Returns the country ISO code (alpha-2), such as "CH" for Switzerland
	 */
	@Column(length = 2, nullable = false)
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	/**
	 * Returns the country name such as "Switzerland"
	 */
	@Column(length = 64, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitCountry(this, input);
	}
}
