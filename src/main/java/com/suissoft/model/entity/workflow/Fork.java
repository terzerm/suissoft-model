package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_FORK")
public class Fork extends Branch {

	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitFork(this, input);
	}
	
}
