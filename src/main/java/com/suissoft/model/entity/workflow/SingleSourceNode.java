package com.suissoft.model.entity.workflow;

import java.util.Collections;
import java.util.Set;

public interface SingleSourceNode extends Node {
	default int getSourceMaxCount() {
		return 1;
	}
	default void addSource(WorkflowNode sourceNode) {
		final Set<WorkflowEdge> sourceEdges = getSourceEdges();
		if (sourceEdges != null && !sourceEdges.isEmpty()) {
			throw new IllegalStateException(getClass().getSimpleName() + " can only have 1 source");
		}
		setSource(sourceNode);
	}
	default WorkflowNode getSource() {
		final Set<WorkflowEdge> sourceEdges = getSourceEdges();
		return sourceEdges == null || sourceEdges.isEmpty() ? null : sourceEdges.iterator().next().getSource();
	}
	default void setSource(WorkflowNode source) {
		setSourceEdges(Collections.singleton(WorkflowNode.createEdge(source, (WorkflowNode)this)));
	}
}
