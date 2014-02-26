package com.suissoft.model.app.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.app.partner.entity.Client;
import com.suissoft.model.entity.AbstractEntity;

@Entity
@Table(name="T_ORDER")
public class Order extends AbstractEntity {

	private Client orderer;
	private Product product;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	@ManyToOne
	public Client getOrderer() {
		return orderer;
	}
	
	public void setOrderer(Client orderer) {
		this.orderer = orderer;
	}
	
	@ManyToOne
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
}
