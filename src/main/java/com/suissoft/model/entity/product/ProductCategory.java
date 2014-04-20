package com.suissoft.model.entity.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.entity.EntityVisitor;

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
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitProductCategory(this, input);
	}
}
