package com.suissoft.model.dao;

import java.util.List;

import com.suissoft.model.Entity;

/**
 * Data access object (DAO) for an entity.
 * 
 * @param <E>
 *            the entity type served by this DAO
 */
public interface Dao<E extends Entity> {

	/**
	 * Returns the pojo class that represents the entity served by this DAO
	 * 
	 * @return the class representing the entities served by this DAO
	 */
	Class<E> getEntityClass();

	/**
	 * Returns a single entity looked up by the specified ID (aka primary key),
	 * or null if no such entity exists.
	 * 
	 * @param id
	 *            the ID or primary key of the entity
	 * @return the entity or null if not found
	 */
	E findById(long id);

	/**
	 * Returns a list with all entities.
	 * 
	 * @return all entities, possibly an empty list but never null
	 */
	List<E> findAll();

	/**
	 * Creates a new entity with no data, for instance for later insertion
	 * through {@link #insertOrUpdate(Entity) insertOrUpdate(..)}. No
	 * persistence operation is performed by this operation.
	 * 
	 * @return a new empty entity
	 */
	E create();

	/**
	 * Inserts or updates the given entity
	 * 
	 * @param entity
	 *            the entity with the new data
	 * @return the inserted or updated entity, may be the same instance as
	 *         {@code entity}
	 */
	E insertOrUpdate(E entity);

	/**
	 * Deletes the entity specified by ID, or does nothing if no such entity
	 * exists.
	 * 
	 * @param id
	 *            the ID (aka primary key) of the entity to delete
	 */
	boolean delete(long id);

	/**
	 * Deletes the specified entity, throwing an exception if the specified
	 * entity does not exist or is a detached entity (e.g. because it was
	 * cloned).
	 * 
	 * @param entity
	 *            the entity to delete
	 * @throws IllegalArgumentException
	 *             if the specified entity does not exist or is detached
	 */
	void delete(E entity);
}
