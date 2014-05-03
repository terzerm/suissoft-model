package com.suissoft.model.entity.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_LAYOUT_SPEC")
public class LayoutSpec extends AbstractEntity {

	private WorkflowElement element;
	private String value;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true)
	public WorkflowElement getElement() {
		return element;
	}

	public void setElement(WorkflowElement element) {
		this.element = element;
	}
	
	@Column(length = 256, nullable=false)
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitLayoutSpec(this, input);
	}

}
