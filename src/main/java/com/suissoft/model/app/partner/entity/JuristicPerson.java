package com.suissoft.model.app.partner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.suissoft.model.app.partner.PartnerVisitor;
import com.suissoft.model.app.partner.dao.JuristicPersonDao;
import com.suissoft.model.app.partner.dao.impl.JuristicPersonDaoImpl;
import com.suissoft.model.persistence.UseDao;

/**
 * Juristic person entity containing the objects that make up the data of a juristic person.
 */
@Entity
@Table(name="T_JURISTIC_PERSON")
@UseDao(type=JuristicPersonDao.class, impl=JuristicPersonDaoImpl.class)
public class JuristicPerson extends Partner {
	private String name;

	private Client client;
	private Supplier supplier;
	private Carrier carrier;
	private ServiceProvider serviceProvider;
	private OwnCompany ownCompany;
	private OwnEmployee ownEmployee;
	
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
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Carrier getCarrier() {
		return carrier;
	}
	
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}
	
	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	
	public OwnCompany getOwnCompany() {
		return ownCompany;
	}
	
	public void setOwnCompany(OwnCompany ownCompany) {
		this.ownCompany = ownCompany;
	}
	
	public OwnEmployee getOwnEmployee() {
		return ownEmployee;
	}
	
	public void setOwnEmployee(OwnEmployee ownEmployee) {
		this.ownEmployee = ownEmployee;
	}
	
	@Override
	public <I, R> R accept(PartnerVisitor<I, R> visitor, I input) {
		return visitor.visitJuristicPerson(this, input);
	}
}
