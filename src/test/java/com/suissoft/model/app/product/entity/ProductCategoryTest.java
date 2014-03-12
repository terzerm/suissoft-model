package com.suissoft.model.app.product.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.app.product.entity.ProductCategory;

public class ProductCategoryTest {

	private ProductCategory productCategory;
	
	@Before
	public void setup() {
		productCategory = new ProductCategory();
	}
	
	@Test
	public void testNameGetterAndSetter() {
		String name = "Food";
		productCategory.setName(name);
		assertEquals(name, productCategory.getName());
	}
}
