package com.suissoft.persistence.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.suissoft.model.dao.JuristicPersonDao;
import com.suissoft.model.partner.JuristicPerson;

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
