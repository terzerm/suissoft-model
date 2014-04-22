package com.suissoft.model.entity.workflow;


public interface Source extends FlowElement {
	
	Target getTarget();
	void setTarget(Target target);

}
