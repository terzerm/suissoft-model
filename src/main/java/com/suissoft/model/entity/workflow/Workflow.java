package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_WORKFLOW")
public class Workflow extends WorkflowElement {

	private List<WorkflowNode> nodes;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="workflow", orphanRemoval=true)
	public List<WorkflowNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<WorkflowNode> nodes) {
		this.nodes = nodes;
	}
	public void addNode(WorkflowNode node) {
		node.setWorkflow(this);
		nodes.add(node);
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitWorkflow(this, input);
	}

}
