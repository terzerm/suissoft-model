package com.suissoft.model.app.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;

@Entity
@Table(name="T_PRODUCT_CATEGORY")
public class ProductCategory extends AbstractEntity {

	private String name;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
