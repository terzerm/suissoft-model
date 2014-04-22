package com.suissoft.model.entity.workflow;

import java.util.List;

import com.suissoft.model.entity.Entity;

public interface FlowElement extends Entity {

	List<LayoutSpec> getLayoutSpecs();
	void setLayoutSpecs(List<LayoutSpec> layoutSpecs);

}
