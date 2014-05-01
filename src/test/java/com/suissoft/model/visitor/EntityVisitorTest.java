package com.suissoft.model.visitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Random;

import org.junit.Test;

import com.suissoft.model.entity.Entity;

public class EntityVisitorTest {

	private final Entity[] ENTITIES = VisitorTestUtil.merge(PartnerVisitorTest.ENTITIES, ProductVisitorTest.ENTITIES, WorkflowVisitorTest.ENTITIES); 

	@Test
	public void shouldAcceptEntityVisitor() {
		//given
		final Random rnd = new Random();
		final int visitMethodCount = VisitorTestUtil.getVisitMethodCount(EntityVisitor.class);
		assertEquals(visitMethodCount, ENTITIES.length);
		final EntityVisitor<Integer, String> visitor = new EntityVisitor<Integer, String>() {};

		for (final Entity entity : ENTITIES) {
			//when
			final Integer input = rnd.nextInt(); 
			final String result = entity.accept(visitor, input);

			//then
			assertNull(entity.toString(), result);
		}
	}

}
