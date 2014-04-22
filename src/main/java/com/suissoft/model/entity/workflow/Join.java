package com.suissoft.model.entity.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_JOIN")
abstract public class Join extends Unbranch {

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}
	
}