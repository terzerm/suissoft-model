package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.suissoft.model.entity.AbstractEntity;

@Embeddable
abstract public class AbstractFlowElement extends AbstractEntity implements FlowElement {
	
	private List<LayoutSpec> layoutSpecs;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="owner", orphanRemoval=true)
	public List<LayoutSpec> getLayoutSpecs() {
		return layoutSpecs;
	}
	
	public void setLayoutSpecs(List<LayoutSpec> layoutSpecs) {
		this.layoutSpecs = layoutSpecs;
	}
}
