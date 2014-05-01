package com.suissoft.model.visitor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

import com.suissoft.model.entity.Entity;
import com.suissoft.model.entity.product.Order;
import com.suissoft.model.entity.product.Product;
import com.suissoft.model.entity.product.ProductCategory;
import com.suissoft.model.entity.product.Quote;

public class ProductVisitorTest {

	private static class TestVisitor implements ProductVisitor<Integer, String> {

		private Map<Class<?>, List<Object>> entityByType = new HashMap<Class<?>, List<Object>>();

		private String visit(Object entity, Integer input) {
			Objects.requireNonNull(entity, "entity cannot be null");
			List<Object> entities = entityByType.get(entity.getClass());
			if (entities == null) {
				entities = new ArrayList<>();
				entityByType.put(entity.getClass(), entities);
			}
			entities.add(entity);
			return input == null ? null : input.toString();
		}

		public int getEntityTypeCount() {
			return entityByType.size();
		}

		public int getCountFor(Class<?> entityClass) {
			final List<Object> entities = entityByType.get(entityClass);
			return entities == null ? 0 : entities.size();
		}

		public String visitOrder(Order order, Integer input) {
			return visit(order, input);
		}

		public String visitProduct(Product product, Integer input) {
			return visit(product, input);
		}

		public String visitProductCategory(ProductCategory productCategory, Integer input) {
			return visit(productCategory, input);
		}

		public String visitQuote(Quote quote, Integer input) {
			return visit(quote, input);
		}
	}

	private static final Entity[] ENTITIES = new Entity[] {new Order(), new Product(), new ProductCategory(), new Quote()};

	@Test
	public void shouldVisitForEveryEntityOnce() {
		//given
		final TestVisitor sharedVisitor = new TestVisitor();

		for (int i = 0; i < ENTITIES.length; i++) {
			//given
			final TestVisitor individualVisitor = new TestVisitor();

			//when
			final String result1 = ENTITIES[i].accept(sharedVisitor, i);
			final String result2 = ENTITIES[i].accept(individualVisitor, i * i);

			//then
			assertEquals(ENTITIES[i].toString(), 1, sharedVisitor.getCountFor(ENTITIES[i].getClass()));
			assertEquals(ENTITIES[i].toString(), 1, individualVisitor.getCountFor(ENTITIES[i].getClass()));
			assertEquals(ENTITIES[i].toString(), 1, individualVisitor.getEntityTypeCount());
			assertEquals(ENTITIES[i].toString(), String.valueOf(i), result1);
			assertEquals(ENTITIES[i].toString(), String.valueOf(i * i), result2);
		}

		//then
		assertEquals(EntityVisitorTest.getVisitMethodCount(ProductVisitor.class), sharedVisitor.getEntityTypeCount());
	}

}
