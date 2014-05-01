package com.suissoft.model.entity.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.visitor.EntityVisitor;

public class LayoutSpec extends AbstractEntity {

	private FlowElement owner;
	private String value;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true)
	public FlowElement getOwner() {
		return owner;
	}

	public void setOwner(FlowElement owner) {
		this.owner = owner;
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
