package com.suissoft.model.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Juristic person entity containing the objects that make up the data of a juristic person.
 */
@Entity
@Table(name="T_JURISTIC_PERSON")
public class JuristicPerson extends Partner {
	private String name;

	/**
	 * Returns the juristic person's name
	 * @return the name of this juristic person
	 */
	@Column(length = 64, nullable = false)
	public String getName() {
		return name;
	}
	/**
	 * Set the juristic person's name
	 * @param name the name of this juristic person
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public <I, R> R accept(PartnerVisitor<I, R> visitor, I input) {
		return visitor.visitJuristicPerson(this, input);
	}
}
