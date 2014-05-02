package com.suissoft.model.entity.workflow;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_WORKFLOW_NODE")
abstract public class WorkflowNode extends WorkflowElement implements Node {
	
	private Workflow workflow;
	private Set<WorkflowEdge> sourceEdges;
	private Set<WorkflowEdge> targetEdges;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true)
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="target", orphanRemoval=true)
	public Set<WorkflowEdge> getSourceEdges() {
		return sourceEdges;
	}
	public void setSourceEdges(Set<WorkflowEdge> sourceEdges) {
		final int max = getSourceMaxCount();
		if (sourceEdges != null && sourceEdges.size() > max) {
			throw new IllegalArgumentException(getClass().getSimpleName() + " can have a max of " + max + " sources: " + sourceEdges);
		}
		this.sourceEdges = sourceEdges;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="source", orphanRemoval=true)
	public Set<WorkflowEdge> getTargetEdges() {
		return targetEdges;
	}
	public void setTargetEdges(Set<WorkflowEdge> targetEdges) {
		final int max = getTargetMaxCount();
		if (targetEdges != null && targetEdges.size() > max) {
			throw new IllegalArgumentException(getClass().getSimpleName() + " can have a max of " + max + " targets: " + targetEdges);
		}
		this.targetEdges = targetEdges;
	}
	
	// helper methods to create edges
	protected static WorkflowEdge createEdge(WorkflowNode source, WorkflowNode target) {
		final WorkflowEdge edge = new WorkflowEdge();
		edge.setSource(source);
		edge.setTarget(target);
		return edge;
	}
}
