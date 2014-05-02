package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_CASE_DATA")
public class CaseData extends WorkflowNode implements SingleSourceNode, SingleTargetNode {

	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitCaseData(this, input);
	}

}
