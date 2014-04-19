package com.suissoft.model.app.partner.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.entity.EntityVisitor;

@Entity
@Table(name="T_RELATIONSHIP")
public class Relationship extends AbstractEntity {

	private RelationshipType relationshipType;
	
	private Partner partnerFrom;
	private Partner partnerTo;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	@ManyToOne
	public RelationshipType getRelationshipType() {
		return relationshipType;
	}
	
	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}
	
	@ManyToOne
	public Partner getPartnerFrom() {
		return partnerFrom;
	}
	
	public void setPartnerFrom(Partner partnerFrom) {
		this.partnerFrom = partnerFrom;
	}
	
	@ManyToOne
	public Partner getPartnerTo() {
		return partnerTo;
	}
	
	public void setPartnerTo(Partner partnerTo) {
		this.partnerTo = partnerTo;
	}
	
	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitRelationship(this, input);
	}
}
