package com.suissoft.model.entity;

import com.suissoft.model.entity.partner.Address;
import com.suissoft.model.entity.partner.AddressType;
import com.suissoft.model.entity.partner.Carrier;
import com.suissoft.model.entity.partner.Client;
import com.suissoft.model.entity.partner.ContactInfo;
import com.suissoft.model.entity.partner.ContactInfoType;
import com.suissoft.model.entity.partner.Country;
import com.suissoft.model.entity.partner.JuristicPerson;
import com.suissoft.model.entity.partner.NaturalPerson;
import com.suissoft.model.entity.partner.OwnCompany;
import com.suissoft.model.entity.partner.OwnEmployee;
import com.suissoft.model.entity.partner.Relationship;
import com.suissoft.model.entity.partner.RelationshipType;
import com.suissoft.model.entity.partner.ServiceProvider;
import com.suissoft.model.entity.partner.Supplier;
import com.suissoft.model.entity.product.Order;
import com.suissoft.model.entity.product.Product;
import com.suissoft.model.entity.product.ProductCategory;
import com.suissoft.model.entity.product.Quote;

/**
 * Adapter with empty implementation for all visit methods. Subclasses can
 * override individual methods. All default implementations return null.
 *
 * @param <I>
 *            type for input passed to visit method
 * @param <R>
 *            type for result returned by visit method
 */
public class EntityVisitorAdapter<I, R> implements EntityVisitor<I, R> {

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitAddress(Address address, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitAddressType(AddressType addressType, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitCarrier(Carrier carrier, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitClient(Client client, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitContatInfo(ContactInfo contactInfo, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitContactInfoType(ContactInfoType contactInfoType, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitCountry(Country country, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitJuristicPerson(JuristicPerson juristicPerson, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitNaturalPerson(NaturalPerson naturalPerson, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitOrder(Order order, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitOwnCompany(OwnCompany ownCompany, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitOwnEmployee(OwnEmployee ownEmployee, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitProduct(Product product, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitProductCategory(ProductCategory productCategory, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitQuote(Quote quote, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitRelationship(Relationship relationship, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitRelationshipType(RelationshipType relationshipType, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitServiceProvider(ServiceProvider serviceProvider, I input) {
		return null;
	}

	/**
	 * Empty default implementation returning null.
	 * 
	 * @return null
	 */
	public R visitSupplier(Supplier supplier, I input) {
		return null;
	}

}
