package com.suissoft.model.entity.product;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.product.Product;
import com.suissoft.model.entity.product.ProductCategory;

public class ProductTest {

	private Product product;
	
	@Before
	public void setup() {
		product = new Product();
	}
	
	@Test
	public void testProductCategoryGetterAndSetter() {
		ProductCategory productCategory = new ProductCategory();
		product.setProductCategory(productCategory);
		assertEquals(productCategory, product.getProductCategory());
	}
}
