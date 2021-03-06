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
}
