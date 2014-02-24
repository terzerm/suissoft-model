package com.suissoft.model.persistence;

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
}
