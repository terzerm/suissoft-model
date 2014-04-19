package com.suissoft.model.app.partner.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_RELATIONSHIP_TYPE")
public class RelationshipType extends AbstractEntity {

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
		return visitor.visitRelationshipType(this, input);
	}
}
