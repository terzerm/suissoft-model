package com.suissoft.model.app.partner.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_SUPPLIER")
public class Supplier extends AbstractEntity {

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitSupplier(this, input);
	}

}
