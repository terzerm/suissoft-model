package com.suissoft.model.app.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.app.partner.entity.Carrier;
import com.suissoft.model.entity.AbstractEntity;

@Entity
@Table(name="T_QUOTE")
public class Quote extends AbstractEntity {

	private Carrier carrier;
	private String region;
	private ProductCategory productCategory;
	private Integer quantity;
	private Double price;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	@ManyToOne
	public Carrier getCarrier() {
		return carrier;
	}
	
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	@ManyToOne
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

}
