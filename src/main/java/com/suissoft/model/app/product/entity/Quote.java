package com.suissoft.model.app.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.app.partner.entity.Carrier;
import com.suissoft.model.entity.AbstractEntity;

@Entity
@Table(name="T_QUOTE")
public class Quote extends AbstractEntity {

	private Carrier carrier;
	private String region;
	private ProductCategory productCategory;
	private int quantities;
	private double price;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	public Carrier getCarrier() {
		return carrier;
	}
	
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
}
