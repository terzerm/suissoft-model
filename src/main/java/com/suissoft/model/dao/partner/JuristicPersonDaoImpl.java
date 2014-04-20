package com.suissoft.model.dao.partner;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.suissoft.model.dao.impl.AbstractPartnerDaoImpl;
import com.suissoft.model.entity.partner.JuristicPerson;

@ApplicationScoped
@PersistenceUnit
public class JuristicPersonDaoImpl extends AbstractPartnerDaoImpl<JuristicPerson> implements JuristicPersonDao {

	@Inject
	public JuristicPersonDaoImpl(EntityManagerFactory entityManagerFactory) {
		super(JuristicPerson.class, entityManagerFactory);
	}

	@Override
	protected Predicate buildPredicateForSingleSearchTerm(Root<JuristicPerson> root, CriteriaBuilder builder, String searchTerm) {
		Predicate predicate = builder.like(builder.lower(root.<String>get("name")), "%" + searchTerm.toLowerCase() + "%");
		return predicate;
	}

}
