package com.suissoft.model.entity;

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
	 * @param visitor	the visitor to callback
	 * @param input		the input to pass to the visit method of the visitor
	 * @return the result returned by the visit method of the visitor
	 */
	<I, R> R accept(EntityVisitor<I, R> visitor, I input);
}
