package com.suissoft.model.partner;

/**
 * Juristic person entity containing the objects that make up the data of a juristic person.
 */
public class JuristicPerson extends Partner {
	private String name;

	/**
	 * Returns the juristic person's name
	 * @return the name of this juristic person
	 */
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
	
}
