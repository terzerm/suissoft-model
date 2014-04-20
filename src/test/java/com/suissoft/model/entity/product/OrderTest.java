package com.suissoft.model.entity.product;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.partner.Client;
import com.suissoft.model.entity.product.Order;
import com.suissoft.model.entity.product.Product;

public class OrderTest {

	private Order order;
	
	@Before
	public void setup() {
		order = new Order();
	}
	
	@Test
	public void testOrdererGetterAndSetter() {
		Client orderer = new Client();
		order.setOrderer(orderer);
		assertEquals(orderer, order.getOrderer());
	}
	
	@Test
	public void testProductGetterAndSetter() {
		Product product = new Product();
		order.setProduct(product);
		assertEquals(product, order.getProduct());
	}
}
