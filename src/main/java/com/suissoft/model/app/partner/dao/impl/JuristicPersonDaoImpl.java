package com.suissoft.model.app.partner.dao.impl;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.suissoft.model.app.partner.dao.JuristicPersonDao;
import com.suissoft.model.app.partner.entity.JuristicPerson;


public class JuristicPersonDaoImpl extends AbstractPartnerDaoImpl<JuristicPerson> implements JuristicPersonDao {

	@Inject
	public JuristicPersonDaoImpl(EntityManagerFactory entityManagerFactory) {
		super(JuristicPerson.class, entityManagerFactory);
	}
	
	@Override
	protected Predicate buildPredicateForSingleSearchTerm(Root<JuristicPerson> root, CriteriaBuilder builder, String searchTerm) {
		Predicate predicate = builder.like(root.<String>get("name"), "%" + searchTerm + "%");
		return predicate;
	}

}
