package com.suissoft.model.entity.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_WORKFLOW_EDGE",//
	uniqueConstraints=@UniqueConstraint(name = "UQ_SOURCE_TARGET", columnNames={"SOURCE_ID", "TARGET_ID"})
)
public class WorkflowEdge extends WorkflowElement {

	private WorkflowNode source;
	private WorkflowNode target;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true, name="SOURCE_ID")
	public WorkflowNode getSource() {
		return source;
	}
	public void setSource(WorkflowNode source) {
		this.source = source;
	}
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true, name="TARGET_ID")
	public WorkflowNode getTarget() {
		return target;
	}
	public void setTarget(WorkflowNode target) {
		this.target = target;
	}

	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitWorkflowEdge(this, input);
	}

}
