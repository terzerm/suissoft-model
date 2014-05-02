package com.suissoft.model.entity.workflow;

import java.util.Collections;
import java.util.Set;

public interface SingleTargetNode extends Node {
	default int getTargetMaxCount() {
		return 1;
	}
	default void addTarget(WorkflowNode targetNode) {
		final Set<WorkflowEdge> targetEdges = getTargetEdges();
		if (targetEdges != null && !targetEdges.isEmpty()) {
			throw new IllegalStateException(getClass().getSimpleName() + " can only have 1 target");
		}
		setTarget(targetNode);
	}
	default WorkflowNode getTarget() {
		final Set<WorkflowEdge> targetEdges = getTargetEdges();
		return targetEdges == null || targetEdges.isEmpty() ? null : targetEdges.iterator().next().getTarget();
	}
	default void setTarget(WorkflowNode target) {
		setTargetEdges(Collections.singleton(WorkflowNode.createEdge(target, (WorkflowNode)this)));
	}
}
