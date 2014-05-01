package com.suissoft.model.visitor;

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

/**
 * Visitor for workflow {@link Entity}.
 *
 * @param <I> type for input passed to visit method
 * @param <R> type for result returned by visit method
 */
public interface WorkflowVisitor<I, R> {
	R visitActivity(Activity activity, I input);
	R visitAlternative(Alternative alternative, I input);
	R visitCaseData(CaseData caseData, I input);
	R visitEnd(End end, I input);
	R visitFork(Fork fork, I input);
	R visitJoin(Join join, I input);
	R visitLayoutSpec(LayoutSpec layoutSpec, I input);
	R visitMerge(Merge merge, I input);
	R visitPartition(Partition partition, I input);
	R visitStart(Start start, I input);
	R visitWorkflow(Workflow workflow, I input);
	
	public interface Adapter<R, I> extends WorkflowVisitor<I, R> {
		default R visitActivity(Activity activity, I input) {return null;}
		default R visitAlternative(Alternative alternative, I input) {return null;}
		default R visitCaseData(CaseData caseData, I input) {return null;}
		default R visitEnd(End end, I input) {return null;}
		default R visitFork(Fork fork, I input) {return null;}
		default R visitJoin(Join join, I input) {return null;}
		default R visitLayoutSpec(LayoutSpec layoutSpec, I input) {return null;}
		default R visitMerge(Merge merge, I input) {return null;}
		default R visitPartition(Partition partition, I input) {return null;}
		default R visitStart(Start start, I input) {return null;}
		default R visitWorkflow(Workflow workflow, I input) {return null;}
	}
	class EntityVisitorAdapter<I, R> implements EntityVisitor.Adapter<I, R> {
		private final WorkflowVisitor<I, R> wrapped;
		public EntityVisitorAdapter(WorkflowVisitor<I, R> wrapped) {
			this.wrapped = wrapped;
		}
		public R visitActivity(Activity activity, I input) {return wrapped.visitActivity(activity, input);}
		public R visitAlternative(Alternative alternative, I input) {return wrapped.visitAlternative(alternative, input);}
		public R visitCaseData(CaseData caseData, I input) {return wrapped.visitCaseData(caseData, input);}
		public R visitEnd(End end, I input) {return wrapped.visitEnd(end, input);}
		public R visitFork(Fork fork, I input) {return wrapped.visitFork(fork, input);}
		public R visitJoin(Join join, I input) {return wrapped.visitJoin(join, input);}
		public R visitLayoutSpec(LayoutSpec layoutSpec, I input) {return wrapped.visitLayoutSpec(layoutSpec, input);}
		public R visitMerge(Merge merge, I input) {return wrapped.visitMerge(merge, input);}
		public R visitPartition(Partition partition, I input) {return wrapped.visitPartition(partition, input);}
		public R visitStart(Start start, I input) {return wrapped.visitStart(start, input);}
		public R visitWorkflow(Workflow workflow, I input) {return wrapped.visitWorkflow(workflow, input);}
	}
}
