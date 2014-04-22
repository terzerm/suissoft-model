package com.suissoft.model.entity.workflow;

import javax.persistence.OneToOne;

import com.suissoft.model.entity.EntityVisitor;


public class Start extends AbstractFlowElement implements Source {

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
		//FIXME impl
		throw new RuntimeException("not implemented");
	}
}
