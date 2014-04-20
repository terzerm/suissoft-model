package com.suissoft.model.dao.partner;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.suissoft.model.dao.impl.AbstractPartnerDaoImpl;
import com.suissoft.model.entity.partner.NaturalPerson;

@ApplicationScoped
@PersistenceUnit
public class NaturalPersonDaoImpl extends AbstractPartnerDaoImpl<NaturalPerson> implements NaturalPersonDao {

	@Inject
	public NaturalPersonDaoImpl(EntityManagerFactory entityManagerFactory) {
		super(NaturalPerson.class, entityManagerFactory);
	}

	@Override
	public List<NaturalPerson> findByLastName(String lastName) {
		final CriteriaBuilder builder = getEntityManagerFactory().getCriteriaBuilder();
		final CriteriaQuery<NaturalPerson> query = builder.createQuery(getEntityClass());
		final Root<NaturalPerson> root = query.from(getEntityClass());
		query.where(builder.equal(root.get("lastName"), lastName));
		return findByQuery(query);
	}
	
	@Override
	public List<NaturalPerson> findByFirstAndLastName(String firstName, String lastName) {
		final CriteriaBuilder builder = getEntityManagerFactory().getCriteriaBuilder();
		final CriteriaQuery<NaturalPerson> query = builder.createQuery(getEntityClass());
		final Root<NaturalPerson> root = query.from(getEntityClass());
		query.where(builder.and(
				builder.equal(root.get("firstName"), firstName),
				builder.equal(root.get("lastName"), lastName)
		));
		return findByQuery(query);
	}
	
	@Override
	protected Predicate buildPredicateForSingleSearchTerm(Root<NaturalPerson> root, CriteriaBuilder builder, String searchTerm) {
		Predicate predicate = builder.or(
				builder.like(builder.lower(root.<String>get("firstName")), "%" + searchTerm.toLowerCase() + "%"),
				builder.like(builder.lower(root.<String>get("lastName")), "%" + searchTerm.toLowerCase() + "%")
		);
		return predicate;
	}

}
