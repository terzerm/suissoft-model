package com.suissoft.model.dao;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@PersistenceContext
public class EntityManagerDao<E> extends AbstractDao<E> implements Dao<E> {
	
	private EntityManager entityManager;
	private CriteriaQuery<E> selectAll;

	public EntityManagerDao(Class<E> entityClass, EntityManager entityManager) {
		super(entityClass);
		requireNonNull(entityManager, "entityManager cannot be null");
		this.entityManager = entityManager;
		this.selectAll = createSelectAllQuery();
	}
	
	private CriteriaQuery<E> createSelectAllQuery() {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<E> query = builder.createQuery(getEntityClass());
		query.from(getEntityClass());
		return query;
	}

	@Override
	public E findById(long id) {
		return entityManager.find(getEntityClass(), id);
	}
	
	@Override
	public List<E> findAll() {
		final TypedQuery<E> typedQuery = entityManager.createQuery(selectAll);
		return typedQuery.getResultList();
	}

	@Override
	public E insertOrUpdate(E entity) {
		final E result;
		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			if (entityManager.contains(entity)) {
				result = entityManager.merge(entity);
			} else {
				entityManager.persist(entity);
				result = entity;
			}
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
	public void delete(E entity) {
		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			entityManager.remove(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

}
