package com.suissoft.model.persistence;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.Guice;

/**
 * Specifies a specialised {@link Dao} interface and implementation type for the
 * annotated entity. This is for instance useful if the specialisd DAO offers
 * additional find methods or other entity specific functionality.
 * <p/>
 * Omitting the {@code @UseDao} annotation has the same effect as the annotation
 * {@code @UseDao(type=Dao.class, impl=EntityManagerDao.class)}.
 * <p>
 * Note that the generic entity parameter {@code <E>} of the {@link Dao} must be
 * compatible with the annotated class, usually {@code <E>} equals the annotated
 * class. Violation of this constraint leads to runtime errors during
 * {@link Guice} injection. Furthermore, the dao implementation specified via
 * {@link #impl()} attribute must be an implementation of the Dao interface that
 * is returned by {@link #type()}.
 * 
 * <pre>
 *    Example:
 * 
 *    &#064;Entity
 *    &#064;Table(name="T_NATURAL_PERSON")
 *    &#064;UseDao(type=NaturalPersonDao.class, impl=NaturalPersonDaoImpl.class)
 *    public class NaturalPerson { ... }
 * </pre>
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface UseDao {
	/**
	 * The DAO interface to use for the annotated entity.
	 * <p>
	 * Note that the generic entity parameter {@code <E>} of the returned
	 * {@link Dao} type must be compatible with the annotated class. In most
	 * cases, {@code <E>} equals the annotated class. Violation of this
	 * constraint may lead to runtime errors.
	 * 
	 * @return the DAO interface type for the annotated entity
	 */
	Class<? extends Dao<?>> type();

	/**
	 * The DAO implementation to use for the annotated entity.
	 * <p>
	 * Note that the class returned here must be an implementation of the Dao
	 * interface that is returned by {@link #type()}.
	 * 
	 * @return the DAO implementation type for the annotated entity
	 */
	Class<? extends Dao<?>> impl();
}
