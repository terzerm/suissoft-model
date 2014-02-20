package com.suissoft.persistence.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.suissoft.model.partner.Partner;

public abstract class AbstractPartnerDaoImpl<T extends Partner> extends EntityManagerDao<T> {

	@Inject
	public AbstractPartnerDaoImpl(Class<T> entityClass, EntityManagerFactory entityManagerFactory) {
		super(entityClass, entityManagerFactory);
	}

	protected abstract Predicate buildPredicateForSingleSearchTerm(Root<T> root, CriteriaBuilder builder, String searchTerm);

	public List<T> findBySearchTerms(List<String> searchTerms) {
		final CriteriaBuilder builder = getEntityManagerFactory().getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(getEntityClass());
		final Root<T> root = query.from(getEntityClass());
		
		Predicate predicate = buildPredicateForSearchTermsList(root, builder, searchTerms);
		query.where(predicate);
		return findByQuery(query);
	}
	
	private Predicate buildPredicateForSearchTermsList(Root<T> root, CriteriaBuilder builder, List<String> searchTerms) {
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
