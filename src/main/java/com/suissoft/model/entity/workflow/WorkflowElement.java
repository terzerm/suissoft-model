package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.suissoft.model.entity.AbstractEntity;

@Embeddable
abstract public class WorkflowElement extends AbstractEntity implements FlowElement {
	
	private Workflow workflow;
	private String name;
	private String description;
	private List<LayoutSpec> layoutSpecs;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(nullable=false, updatable=false, insertable=true)
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * Returns the unique name for this element
	 * 
	 * @return the name for this element
	 */
	@Column(length = 64, nullable = false, unique = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the long text description for this element
	 * 
	 * @return the long text workflow description
	 */
	@Column(length = 256)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="owner", orphanRemoval=true)
	public List<LayoutSpec> getLayoutSpecs() {
		return layoutSpecs;
	}
	
	public void setLayoutSpecs(List<LayoutSpec> layoutSpecs) {
		this.layoutSpecs = layoutSpecs;
	}

}
