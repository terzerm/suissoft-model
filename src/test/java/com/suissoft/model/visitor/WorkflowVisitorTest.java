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
import com.suissoft.model.entity.workflow.Activity;
import com.suissoft.model.entity.workflow.Alternative;
import com.suissoft.model.entity.workflow.CaseData;
import com.suissoft.model.entity.workflow.End;
import com.suissoft.model.entity.workflow.Fork;
import com.suissoft.model.entity.workflow.Join;
import com.suissoft.model.entity.workflow.LayoutSpec;
import com.suissoft.model.entity.workflow.Merge;
import com.suissoft.model.entity.workflow.Partition;
import com.suissoft.model.entity.workflow.Start;
import com.suissoft.model.entity.workflow.Workflow;
import com.suissoft.model.entity.workflow.WorkflowEdge;

public class WorkflowVisitorTest {

	static final Entity[] ENTITIES = { new Activity(), new Alternative(), new CaseData(), new End(), new Fork(), new Join(), new LayoutSpec(), new Merge(), new Partition(), new Start(), new Workflow(), new WorkflowEdge() };

	private static class TestVisitor implements WorkflowVisitor<Integer, String> {

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

		@Override
		public String visitActivity(Activity activity, Integer input) {
			return visit(activity, input);
		}

		@Override
		public String visitAlternative(Alternative alternative, Integer input) {
			return visit(alternative, input);
		}

		@Override
		public String visitCaseData(CaseData caseData, Integer input) {
			return visit(caseData, input);
		}

		@Override
		public String visitEnd(End end, Integer input) {
			return visit(end, input);
		}

		@Override
		public String visitFork(Fork fork, Integer input) {
			return visit(fork, input);
		}

		@Override
		public String visitJoin(Join join, Integer input) {
			return visit(join, input);
		}

		@Override
		public String visitLayoutSpec(LayoutSpec layoutSpec, Integer input) {
			return visit(layoutSpec, input);
		}

		@Override
		public String visitMerge(Merge merge, Integer input) {
			return visit(merge, input);
		}

		@Override
		public String visitPartition(Partition partition, Integer input) {
			return visit(partition, input);
		}

		@Override
		public String visitStart(Start start, Integer input) {
			return visit(start, input);
		}

		@Override
		public String visitWorkflow(Workflow workflow, Integer input) {
			return visit(workflow, input);
		}

		public String visitWorkflowEdge(WorkflowEdge workflowEdge, Integer input) {
			return visit(workflowEdge, input);
		}
		
	}

	@Test
	public void shouldVisitForEveryEntityOnce() {
		//given
		final Random rnd = new Random();
		final int visitMethodCount = VisitorTestUtil.getVisitMethodCount(WorkflowVisitor.class);
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
