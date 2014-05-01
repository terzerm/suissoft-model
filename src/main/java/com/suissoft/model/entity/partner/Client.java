package com.suissoft.model.entity.partner;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.suissoft.model.entity.AbstractEntity;
import com.suissoft.model.entity.product.Order;
import com.suissoft.model.visitor.EntityVisitor;

@Entity
@Table(name="T_CLIENT")
public class Client extends AbstractEntity {

	private List<Order> orders;
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return super.getId();
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="orderer", orphanRemoval=true)
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public <I, R> R accept(EntityVisitor<I, R> visitor, I input) {
		return visitor.visitClient(this, input);
	}
}
