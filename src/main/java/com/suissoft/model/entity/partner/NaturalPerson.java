package com.suissoft.model.entity.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import com.suissoft.model.dao.UseDao;
import com.suissoft.model.dao.partner.NaturalPersonDao;
import com.suissoft.model.dao.partner.NaturalPersonDaoImpl;
import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_NATURAL_PERSON")
@UseDao(type = NaturalPersonDao.class, impl = NaturalPersonDaoImpl.class)
public class NaturalPerson extends Partner {
	
	private String lastName;
	private String firstName;
	private LocalDate birthday;

	private Client client;
	private OwnEmployee ownEmployee;
	
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
	
	@ManyToOne(optional=true)
	@Override
	public Client getClient() {
		return client;
	}
	
	@Override
	public void setClient(Client client) {
		this.client = client;
	}
	
	@OneToOne
	public OwnEmployee getOwnEmployee() {
		return ownEmployee;
	}
	
	public void setOwnEmployee(OwnEmployee ownEmployee) {
		this.ownEmployee = ownEmployee;
	}
	
	@Override
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitNaturalPerson(this, input);
	}
	
}
