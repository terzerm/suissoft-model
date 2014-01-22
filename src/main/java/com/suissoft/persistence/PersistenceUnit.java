package com.suissoft.persistence;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Constants for persistenceUnit entries in the persistence.xml file. The {@link #name()}
 * is identical to the persistenceUnitName.
 */
public enum PersistenceUnit {
	/**
	 * H2 in-memory database 
	 */
	H2_MEMORY(H2_Memory.class), 
	/**
	 * H2 file-based database 
	 */
	H2_FILE(H2_File.class);
	
	private final Class<? extends Annotation> annotation;

	private PersistenceUnit(Class<? extends Annotation> annotation) {
		this.annotation = annotation;
	}
	
	public Class<? extends Annotation> getAnnotation() {
		return annotation;
	}
	
	@Retention(RUNTIME)
	@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
	@BindingAnnotation
	public static @interface H2_Memory {}

	@Retention(RUNTIME)
	@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
	@BindingAnnotation
	public static @interface H2_File {}
	
}
