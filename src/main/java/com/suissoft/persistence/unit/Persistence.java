package com.suissoft.persistence.unit;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import com.google.inject.BindingAnnotation;
import com.suissoft.model.dao.Dao;

/**
 * Annotation for injection of persistence object such as a {@link Dao}
 * indicating which persistence unit to use from the persistence.xml file.
 * <p>
 * The {@link Unit#name() name} of the annotation {@link #value() value}
 * corresponds to the name attribute in the {@code <persistenceUnit>} tag in the
 * persistence.xml file.
 */
@Retention(RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Documented
@BindingAnnotation
@Qualifier
public @interface Persistence {
	/**
	 * Constant for the persistence unit. The {@link #name()} corresponds to the
	 * persistenceUnit name in the persistence.xml file.
	 */
	enum Unit {
		/**
		 * H2 in-memory database
		 */
		H2_MEMORY,
		/**
		 * H2 file-based database
		 */
		H2_FILE;

		private final Persistence annotation = new PersistenceImpl(this);

		/**
		 * Returns an annotation with {@code this} unit as value.
		 */
		public Persistence asAnnotation() {
			return annotation;
		}
	};

	/**
	 * Annotation value specifying the persistence unit to use for the annotated
	 * element.
	 */
	Unit value();
}
