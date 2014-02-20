package com.suissoft.model.dao;

import java.util.List;

import com.suissoft.model.partner.NaturalPerson;

/**
 * Specialised DAO for {@link NaturalPerson}
 */
public interface NaturalPersonDao extends Dao<NaturalPerson> {
	/**
	 * Returns a list with all persons matching the given last name
	 * 
	 * @return all matching entities, possibly an empty list but never null
	 */
	List<NaturalPerson> findByLastName(String lastName);
	
	/**
	 * Returns a list with all persons matching the given names
	 * 
	 * @return all matching entities, possibly an empty list but never null
	 */
	List<NaturalPerson> findByFirstAndLastName(String firstName, String lastName);
	
	/**
	 * Find all natural persons that contain all of the search terms in
	 * first name or last name
	 *  
	 * @param searchTerms
	 * @return All matching natural persons
	 */
	List<NaturalPerson> findBySearchTerms(List<String> searchTerms);
}
