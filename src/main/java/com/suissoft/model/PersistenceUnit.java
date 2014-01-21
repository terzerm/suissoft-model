package com.suissoft.model;

/**
 * Constants for persistenceUnit entries in the persistence.xml file. The {@link #name()}
 * is identical to the persistenceUnitName.
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
