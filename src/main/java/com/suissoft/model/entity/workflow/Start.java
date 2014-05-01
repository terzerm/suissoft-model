package com.suissoft.model.entity.workflow;

import javax.persistence.OneToOne;

import com.suissoft.model.visitor.EntityVisitor;


public class Start extends WorkflowElement implements Source {

	private Target target;
	
	@Override
	@OneToOne
	public Target getTarget() {
		return target;
	}
	
	@Override
	public void setTarget(Target target) {
		this.target = target;
	}

	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitStart(this, input);
	}
}
