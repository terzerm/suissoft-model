package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Embeddable
abstract public class Branch extends AbstractFlowElement implements Junction, Target {

	private Source source;
	private List<Target> targets;

	@OneToOne
	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="source")
	public List<Target> getTargets() {
		return targets;
	}
	
	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

}
