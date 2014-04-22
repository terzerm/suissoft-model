package com.suissoft.model.inject.cdi;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.DefinitionException;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.impl.EntityManagerDao;
import com.suissoft.model.entity.Entity;
import com.suissoft.model.util.GenericsUtil;

/**
 * Producer for generic {@link Dao} instances.
 */
@ApplicationScoped
@PersistenceUnit
@Alternative
@Priority(javax.interceptor.Interceptor.Priority.APPLICATION)
//activates alternative
public class DaoProducer {

	@Inject
	private EntityManagerFactory entityManagerFactory;

	/**
	 * Produces generic Dao's for an injection point. The entity type is derived
	 * from the injection point type. As a consequence, this producer only works
	 * for injection points defining a Dao with a concrete entity type such as
	 * {@code Dao<Country>}; wildcard or variable type expressions such as
	 * {@code Dao<?>} cause a runtime exception.
	 * 
	 * 
	 * @param injectionPoint
	 *            meta data about the Dao injection point
	 * @param <E>
	 *            type of entity for returned Dao
	 * @return a new Dao instance for the appropriate entity type
	 * @throws DefinitionException
	 *             if the entity type cannot be evaluated
	 */
	@Produces
	@Dependent
	@Alternative
	public <E extends Entity> Dao<E> getDao(InjectionPoint injectionPoint) {
		final Type type = injectionPoint.getType();
		//cast should be ok because the injection point must be consistent with the method type variable
		final TypeVariable<?> entityTypeVariable = Dao.class.getTypeParameters()[0];
		final Class<?> entityClass = GenericsUtil.getTypeArgument(type, entityTypeVariable);
		if (entityClass != null) {
			@SuppressWarnings("unchecked")
			//cast should be ok because the injection point must be consistent with the method type variable
			final Class<E> entitySubClass = (Class<E>) entityClass;
			return new EntityManagerDao<E>(entitySubClass, entityManagerFactory);
		} else {
			throw new DefinitionException("cannot evaluate entity type for injected Dao: type=" + type + ", injectionPoint=" + injectionPoint);
		}
	}

}
