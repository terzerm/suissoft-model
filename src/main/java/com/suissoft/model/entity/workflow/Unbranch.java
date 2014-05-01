package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Embeddable
abstract public class Unbranch extends WorkflowElement implements Junction, Source {

	private List<Source> sources;
	private Target target;

	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="target")
	public List<Source> getSources() {
		return sources;
	}
	
	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	@OneToOne
	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}
	
}
