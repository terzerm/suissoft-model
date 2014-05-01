package com.suissoft.model.entity;

import com.suissoft.model.visitor.EntityVisitor;
import com.suissoft.model.visitor.PartnerVisitor;
import com.suissoft.model.visitor.ProductVisitor;
import com.suissoft.model.visitor.WorkflowVisitor;

/**
 * Interface implemented by all entities.
 */
public interface Entity {
	/**
	 * The ID or primary key of this entity.
	 * 
	 * @return the ID or primary key
	 */
	long getId();

	/**
	 * Accept method for visitor calling back into visitor passing this entity
	 * as argument. The best matching visit method of the visitor is called.
	 * 
	 * @param visitor
	 *            the visitor to callback
	 * @param input
	 *            the input to pass to the visit method of the visitor
	 * @param <I>
	 *            the input type
	 * @param <R>
	 *            the return value type
	 * @return the result returned by the visit method of the visitor
	 */
	<I, R> R accept(EntityVisitor<I, R> visitor, I input);
	/**
	 * Accept method for visitor calling back into visitor passing this entity
	 * as argument. The best matching visit method of the visitor is called.
	 * 
	 * @param visitor
	 *            the visitor to callback
	 * @param input
	 *            the input to pass to the visit method of the visitor
	 * @param <I>
	 *            the input type
	 * @param <R>
	 *            the return value type
	 * @return the result returned by the visit method of the visitor
	 */
	default <I, R> R accept(PartnerVisitor<I, R> visitor, I input) {
		return accept(visitor instanceof EntityVisitor ? (EntityVisitor<I, R>)visitor : new PartnerVisitor.EntityVisitorAdapter<I,R>(visitor), input);
	}
	/**
	 * Accept method for visitor calling back into visitor passing this entity
	 * as argument. The best matching visit method of the visitor is called.
	 * 
	 * @param visitor
	 *            the visitor to callback
	 * @param input
	 *            the input to pass to the visit method of the visitor
	 * @param <I>
	 *            the input type
	 * @param <R>
	 *            the return value type
	 * @return the result returned by the visit method of the visitor
	 */
	default <I, R> R accept(ProductVisitor<I, R> visitor, I input) {
		return accept(visitor instanceof EntityVisitor ? (EntityVisitor<I, R>)visitor : new ProductVisitor.EntityVisitorAdapter<I,R>(visitor), input);
	}
	/**
	 * Accept method for visitor calling back into visitor passing this entity
	 * as argument. The best matching visit method of the visitor is called.
	 * 
	 * @param visitor
	 *            the visitor to callback
	 * @param input
	 *            the input to pass to the visit method of the visitor
	 * @param <I>
	 *            the input type
	 * @param <R>
	 *            the return value type
	 * @return the result returned by the visit method of the visitor
	 */
	default <I, R> R accept(WorkflowVisitor<I, R> visitor, I input) {
		return accept(visitor instanceof EntityVisitor ? (EntityVisitor<I, R>)visitor : new WorkflowVisitor.EntityVisitorAdapter<I,R>(visitor), input);
	}
}
