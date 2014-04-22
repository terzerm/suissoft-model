package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_WORKFLOW")
public class Workflow extends Node {

	private List<Activity> activities;

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="owner", orphanRemoval=true)
	public List<Activity> getActivities() {
		return activities;
	}
	
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public void addActivity(Activity activity) {
		activity.setOwner(this);
		activities.add(activity);
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		//FIXME impl
		throw new RuntimeException("not implemented");
	}

}
