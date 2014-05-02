package com.suissoft.model.entity.workflow;

import java.util.Set;

import com.suissoft.model.visitor.EntityVisitor;


public class End extends WorkflowNode implements SingleSourceNode {

	public int getTargetMaxCount() {
		return 0;
	}
	@Override
	public void setTargetEdges(Set<WorkflowEdge> targetEdges) {
		if (targetEdges != null && !targetEdges.isEmpty()) {
			throw new IllegalArgumentException(getClass().getSimpleName() + " has no target: " + targetEdges);
		}
		super.setTargetEdges(targetEdges);
	}
	@Override
	public void addTarget(WorkflowNode targetNode) {
		throw new IllegalArgumentException(getClass().getSimpleName() + " has no target: " + targetNode);
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitEnd(this, input);
	}

}
