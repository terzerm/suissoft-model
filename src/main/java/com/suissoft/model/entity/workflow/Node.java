package com.suissoft.model.entity.workflow;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
abstract public class Node extends AbstractFlowElement implements Source, Target {
	
	private Source source;
	private Target target;
	
	@Override
	@OneToOne
	public Source getSource() {
		return source;
	}
	
	@Override
	public void setSource(Source source) {
		this.source = source;
	}
	
	@Override
	@OneToOne
	public Target getTarget() {
		return target;
	}
	
	@Override
	public void setTarget(Target target) {
		this.target = target;
	}
}
