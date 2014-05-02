package com.suissoft.model.entity.workflow;

import java.util.Set;

import com.suissoft.model.visitor.EntityVisitor;


public class Start extends WorkflowNode implements SingleTargetNode {

	public int getSourceMaxCount() {
		return 0;
	}
	@Override
	public void setSourceEdges(Set<WorkflowEdge> sourceEdges) {
		if (sourceEdges != null && !sourceEdges.isEmpty()) {
			throw new IllegalArgumentException(getClass().getSimpleName() + " has no source: " + sourceEdges);
		}
		super.setSourceEdges(sourceEdges);
	}
	@Override
	public void addSource(WorkflowNode sourceNode) {
		throw new IllegalArgumentException(getClass().getSimpleName() + " has no source: " + sourceNode);
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitStart(this, input);
	}
}
