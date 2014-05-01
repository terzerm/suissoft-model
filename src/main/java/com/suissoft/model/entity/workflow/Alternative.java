package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_ALTERNATIVE")
public class Alternative extends Branch {

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitAlternative(this, input);
	}
	
}
