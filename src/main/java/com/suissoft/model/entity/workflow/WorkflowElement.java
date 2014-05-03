package com.suissoft.model.entity.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.suissoft.model.entity.AbstractEntity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
abstract public class WorkflowElement extends AbstractEntity {
	
	private String name;
	private String description;
	private List<LayoutSpec> layoutSpecs;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
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
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="element", orphanRemoval=true)
	public List<LayoutSpec> getLayoutSpecs() {
		return layoutSpecs;
	}
	
	public void setLayoutSpecs(List<LayoutSpec> layoutSpecs) {
		this.layoutSpecs = layoutSpecs;
	}

}
