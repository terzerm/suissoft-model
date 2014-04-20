package com.suissoft.model.entity.product;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.entity.partner.Carrier;
import com.suissoft.model.entity.product.ProductCategory;
import com.suissoft.model.entity.product.Quote;

public class QuoteTest {

	private Quote quote;
	
	@Before
	public void setup() {
		quote = new Quote();
	}
	
	@Test
	public void testCarrierGetterAndSetter() {
		Carrier carrier = new Carrier();
		
		quote.setCarrier(carrier);
		assertEquals(carrier,  quote.getCarrier());
	}

	@Test
	public void testRegionGetterAndSetter() {
		String region = "eden";
		
		quote.setRegion(region);
		assertEquals(region, quote.getRegion());
	}

	@Test
	public void testProductCategoryGetterAndSetter() {
		ProductCategory productCategory = new ProductCategory();
		
		quote.setProductCategory(productCategory);
		assertEquals(productCategory, quote.getProductCategory());
	}

	@Test
	public void testQuantityGetterAndSetter() {
		Integer quantity = Integer.valueOf(100);
		quote.setQuantity(quantity);
		assertEquals(quantity, quote.getQuantity());
	}

	@Test
	public void testPriceGetterAndSetter() {
		Double price = Double.valueOf("125.30");
		quote.setPrice(price);
		assertEquals(price, quote.getPrice());
	}

}
