package com.suissoft.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.suissoft.model.entity.partner.Partner;

public abstract class AbstractPartnerDaoImpl<E extends Partner> extends EntityManagerDao<E> {

	public AbstractPartnerDaoImpl(Class<E> entityClass, EntityManagerFactory entityManagerFactory) {
		super(entityClass, entityManagerFactory);
	}

	protected abstract Predicate buildPredicateForSingleSearchTerm(Root<E> root, CriteriaBuilder builder, String searchTerm);

	public List<E> findBySearchTerms(List<String> searchTerms) {
		final CriteriaBuilder builder = getEntityManagerFactory().getCriteriaBuilder();
		final CriteriaQuery<E> query = builder.createQuery(getEntityClass());
		final Root<E> root = query.from(getEntityClass());
		
		Predicate predicate = buildPredicateForSearchTermsList(root, builder, searchTerms);
		query.where(predicate);
		return findByQuery(query);
	}
	
	private Predicate buildPredicateForSearchTermsList(Root<E> root, CriteriaBuilder builder, List<String> searchTerms) {
		if (searchTerms.size() == 0) {
			throw new IllegalArgumentException("Expected at least one search term");
		}
		Predicate predicateForFirstSearchTerm = buildPredicateForSingleSearchTerm(root, builder, searchTerms.get(0));
		if (searchTerms.size() == 1) {
			return predicateForFirstSearchTerm;
		} else {
			List<String> remainingSearchTerms = searchTerms.subList(1, searchTerms.size());
			Predicate predicateForRemainingSearchTerms = buildPredicateForSearchTermsList(root, builder, remainingSearchTerms);
			return builder.and(predicateForFirstSearchTerm, predicateForRemainingSearchTerms);
		}
	}
	
}
