package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_ACTIVITY")
public class Activity extends WorkflowNode implements SingleSourceNode, SingleTargetNode {

	private Partition partition;

	@ManyToOne
	public Partition getPartition() {
		return partition;
	}

	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitActivity(this, input);
	}

}
