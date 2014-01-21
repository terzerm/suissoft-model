package com.suissoft.model.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name="T_NATURAL_PERSON")
public class NaturalPerson extends Partner {
	private String lastName;
	private String firstName;
	private LocalDate birthday;

	@Column(length = 64, nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(length = 64, nullable=true)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(nullable=true)
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
}