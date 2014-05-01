package com.suissoft.model.visitor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.junit.Test;

import com.suissoft.model.entity.Entity;
import com.suissoft.model.entity.product.Order;
import com.suissoft.model.entity.product.Product;
import com.suissoft.model.entity.product.ProductCategory;
import com.suissoft.model.entity.product.Quote;

public class ProductVisitorTest {

	static final Entity[] ENTITIES = {new Order(), new Product(), new ProductCategory(), new Quote()};

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

	@Test
	public void shouldVisitForEveryEntityOnce() {
		//given
		final Random rnd = new Random();
		final int visitMethodCount = VisitorTestUtil.getVisitMethodCount(ProductVisitor.class);
		final TestVisitor sharedVisitor = new TestVisitor();
		assertEquals(visitMethodCount, ENTITIES.length);

		for (final Entity entity : ENTITIES) {
			//given
			final Integer input1 = rnd.nextInt();
			final Integer input2 = rnd.nextInt();
			final TestVisitor individualVisitor = new TestVisitor();

			//when
			final String result1 = entity.accept(sharedVisitor, input1);
			final String result2 = entity.accept(individualVisitor, input2);

			//then
			assertEquals(entity.toString(), 1, sharedVisitor.getCountFor(entity.getClass()));
			assertEquals(entity.toString(), 1, individualVisitor.getCountFor(entity.getClass()));
			assertEquals(entity.toString(), 1, individualVisitor.getEntityTypeCount());
			assertEquals(entity.toString(), input1.toString(), result1);
			assertEquals(entity.toString(), input2.toString(), result2);
		}

		//then
		assertEquals(visitMethodCount, sharedVisitor.getEntityTypeCount());
	}

}
