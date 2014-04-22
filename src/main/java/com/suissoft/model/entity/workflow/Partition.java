package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_PARTITION")
public class Partition extends AbstractFlowElement {

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		//FIXME impl
		throw new RuntimeException("not implemented");
	}

}
