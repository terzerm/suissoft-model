package com.suissoft.model.entity.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_ACTIVITY")
public class Activity extends Node {

	private Workflow owner;
	private Partition partition;

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true)
	public Workflow getOwner() {
		return owner;
	}

	public void setOwner(Workflow owner) {
		this.owner = owner;
	}

	@ManyToOne
	public Partition getPartition() {
		return partition;
	}

	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		//TODO impl
		throw new RuntimeException("not implemented");
	}

}
