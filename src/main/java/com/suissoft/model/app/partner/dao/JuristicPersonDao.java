package com.suissoft.model.app.partner.dao;

import java.util.List;

import com.suissoft.model.app.partner.entity.JuristicPerson;
import com.suissoft.model.entity.dao.Dao;

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
