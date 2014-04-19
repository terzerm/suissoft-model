package com.suissoft.model.entity;

import com.suissoft.model.app.partner.entity.Address;
import com.suissoft.model.app.partner.entity.AddressType;
import com.suissoft.model.app.partner.entity.Carrier;
import com.suissoft.model.app.partner.entity.Client;
import com.suissoft.model.app.partner.entity.ContactInfo;
import com.suissoft.model.app.partner.entity.ContactInfoType;
import com.suissoft.model.app.partner.entity.Country;
import com.suissoft.model.app.partner.entity.JuristicPerson;
import com.suissoft.model.app.partner.entity.NaturalPerson;
import com.suissoft.model.app.partner.entity.OwnCompany;
import com.suissoft.model.app.partner.entity.OwnEmployee;
import com.suissoft.model.app.partner.entity.Relationship;
import com.suissoft.model.app.partner.entity.RelationshipType;
import com.suissoft.model.app.partner.entity.ServiceProvider;
import com.suissoft.model.app.partner.entity.Supplier;
import com.suissoft.model.app.product.entity.Order;
import com.suissoft.model.app.product.entity.Product;
import com.suissoft.model.app.product.entity.ProductCategory;
import com.suissoft.model.app.product.entity.Quote;

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
