package com.suissoft.model.dao;

import java.util.List;

import com.suissoft.model.partner.JuristicPerson;

public interface JuristicPersonDao extends Dao<JuristicPerson> {

	/**
	 * Find all juristic persons that contain all of the search terms in their name
	 *  
	 * @param searchTerms
	 * @return All matching juristic persons
	 */
	List<JuristicPerson> findBySearchTerms(List<String> searchTerms);

}
