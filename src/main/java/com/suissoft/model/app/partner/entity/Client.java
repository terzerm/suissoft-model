package com.suissoft.model.app.partner.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.suissoft.model.app.product.entity.Order;
import com.suissoft.model.entity.AbstractEntity;

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

}
