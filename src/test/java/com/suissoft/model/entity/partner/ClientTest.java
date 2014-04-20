package com.suissoft.model.entity.partner;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.partner.Client;
import com.suissoft.model.entity.product.Order;

public class ClientTest {

	private Client client;
	
	@Before
	public void setup() {
		client = new Client();
	}
	
	@Test
	public void testOrdersGetterAndSetter() {
		List<Order> orders = new ArrayList<>();
		client.setOrders(orders);
		assertEquals(orders, client.getOrders());
	}
}
