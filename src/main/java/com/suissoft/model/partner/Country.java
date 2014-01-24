package com.suissoft.model.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.suissoft.model.AbstractEntity;

@Entity
@Table(name="T_COUNTRY")
public class Country extends AbstractEntity {
	
	private String abbreviation;
	private String name;
	
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
}
