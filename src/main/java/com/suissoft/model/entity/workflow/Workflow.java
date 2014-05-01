package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_WORKFLOW")
public class Workflow extends Node {

	private List<WorkflowElement> elements;

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="workflow", orphanRemoval=true)
	public List<WorkflowElement> getElements() {
		return elements;
	}
	
	public void setElements(List<WorkflowElement> elements) {
		this.elements = elements;
	}
	
	public void addElement(WorkflowElement element) {
		element.setWorkflow(this);
		elements.add(element);
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitWorkflow(this, input);
	}

}
