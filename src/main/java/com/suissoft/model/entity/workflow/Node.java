package com.suissoft.model.entity.workflow;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface Node {
	Set<WorkflowEdge> getSourceEdges();
	void setSourceEdges(Set<WorkflowEdge> sourceEdges);

	default int getSourceMaxCount() {
		return Integer.MAX_VALUE;
	}
	default void addSource(WorkflowNode source) {
		final WorkflowEdge edge = WorkflowNode.createEdge(source, (WorkflowNode)this);
		//source
		Set<WorkflowEdge> sourceEdges = getSourceEdges();
		if (sourceEdges == null) {
			sourceEdges = new LinkedHashSet<>();
		}
		sourceEdges.add(edge);
		setSourceEdges(sourceEdges);
		//target
		Set<WorkflowEdge> targetEdges = source.getTargetEdges();
		if (targetEdges == null) {
			targetEdges = new LinkedHashSet<>();
		}
		targetEdges.add(edge);
		source.setTargetEdges(targetEdges);
	}
	default Set<WorkflowNode> getSources() {
		return getSourceEdges().stream().map(edge -> edge.getSource()).collect(Collectors.toSet());
	}
	
	Set<WorkflowEdge> getTargetEdges();
	void setTargetEdges(Set<WorkflowEdge> targetEdges);
	default int getTargetMaxCount() {
		return Integer.MAX_VALUE;
	}
	default void addTarget(WorkflowNode targetNode) {
		targetNode.addSource((WorkflowNode)this);
	}
	default Set<WorkflowNode> getTargets() {
		return getTargetEdges().stream().map(edge -> edge.getTarget()).collect(Collectors.toSet());
	}

}
