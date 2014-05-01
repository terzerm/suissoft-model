package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_CASE_DATA")
public class CaseData extends WorkflowElement {

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitCaseData(this, input);
	}

}
