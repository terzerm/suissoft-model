package com.suissoft.model.dao.partner;

import java.util.List;

import com.suissoft.model.dao.Dao;
import com.suissoft.model.entity.partner.JuristicPerson;

/**
 * Specialised DAO for {@link JuristicPerson}
 */
public interface JuristicPersonDao extends Dao<JuristicPerson> {

	/**
	 * Find all juristic persons that contain all of the search terms in their name
	 *  
	 * @param searchTerms
	 * @return All matching juristic persons
	 */
	List<JuristicPerson> findBySearchTerms(List<String> searchTerms);

}
