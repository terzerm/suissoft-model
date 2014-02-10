package com.suissoft.persistence.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.suissoft.model.dao.NaturalPersonDao;
import com.suissoft.model.partner.NaturalPerson;

public class NaturalPersonDaoImpl extends EntityManagerDao<NaturalPerson> implements NaturalPersonDao {
	
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
	
	
}
