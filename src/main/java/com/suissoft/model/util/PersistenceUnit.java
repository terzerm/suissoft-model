package com.suissoft.model.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Constant for the persistence unit as defined in the "persistence.xml" file.
 * <p>
 * The constant {@link #name()} corresponds to the name attribute in the
 * {@code <persistenceUnit>} tag in the persistence.xml file.
 */
public enum PersistenceUnit {
	/**
	 * H2 in-memory database
	 */
	H2_MEMORY,
	/**
	 * H2 file-based database
	 */
	H2_FILE;
	
	/**
	 * Create and return an EntityManagerFactory for the persistence unit with
	 * the {@link #name() name} defined by this constant. 
	 *
	 * @return The factory that creates EntityManagers configured according to the specified persistence unit
	 * @see Persistence#createEntityManagerFactory(String)
	 */
	public EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(name());
	}
}
