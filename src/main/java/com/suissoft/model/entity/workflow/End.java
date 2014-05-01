package com.suissoft.model.entity.workflow;

import javax.persistence.OneToOne;

import com.suissoft.model.visitor.EntityVisitor;


public class End extends WorkflowElement implements Target {

	private Source source;
	
	@Override
	@OneToOne
	public Source getSource() {
		return source;
	}
	
	@Override
	public void setSource(Source source) {
		this.source = source;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitEnd(this, input);
	}

}
