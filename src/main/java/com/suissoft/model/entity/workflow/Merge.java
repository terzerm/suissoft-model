package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_MERGE")
public class Merge extends Unbranch {

	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitMerge(this, input);
	}
	
}
