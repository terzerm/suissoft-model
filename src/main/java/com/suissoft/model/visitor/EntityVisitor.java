package com.suissoft.model.visitor;

import com.suissoft.model.entity.Entity;


/**
 * Visitor for {@link Entity}.
 *
 * @param <I> type for input passed to visit method
 * @param <R> type for result returned by visit method
 */
public interface EntityVisitor<I, R> extends PartnerVisitor<I, R>, ProductVisitor<I, R>, WorkflowVisitor<I, R> {
	//all methods are defined by super-interfaces
	interface Adapter<I, R> extends EntityVisitor<I, R>, PartnerVisitor.Adapter<I, R>, ProductVisitor.Adapter<I, R>, WorkflowVisitor.Adapter<R, I> {
		//all methods are defined by super-interfaces
	}
}
