package com.suissoft.model.persistence;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.suissoft.model.entity.Entity;

@PersistenceUnit
public class EntityManagerDao<E extends Entity> extends AbstractDao<E> implements Dao<E> {
	
	private final EntityManagerFactory entityManagerFactory;
	private final CriteriaQuery<E> selectAllQuery;

	public EntityManagerDao(Class<E> entityClass, EntityManagerFactory entityManagerFactory) {
		super(entityClass);
		requireNonNull(entityManagerFactory, "entityManagerFactory cannot be null");
		this.entityManagerFactory = entityManagerFactory;
		this.selectAllQuery = createSelectAllQuery();
	}
	
	protected EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	protected EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	@Override
	public E findById(long id) {
		final EntityManager entityManager = createEntityManager();
		return entityManager.find(getEntityClass(), id);
	}
	
	@Override
	public List<E> findAll() {
		return findByQuery(selectAllQuery);
	}

	@Override
	public E insertOrUpdate(E entity) {
		final E result;
		final EntityManager entityManager = createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			result = entityManager.merge(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
		return result;
	}

	@Override
	public boolean delete(long id) {
		final EntityManager entityManager = createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			final E entity = entityManager.find(getEntityClass(), id);
			if (entity != null) {
				entityManager.remove(entity);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}
	
	private CriteriaQuery<E> createSelectAllQuery() { 
		final CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
		final CriteriaQuery<E> query = builder.createQuery(getEntityClass());
		query.from(getEntityClass());
		return query;
	}
	
	protected List<E> findByQuery(CriteriaQuery<E> query) {
		final EntityManager entityManager = createEntityManager();
		final TypedQuery<E> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

}
