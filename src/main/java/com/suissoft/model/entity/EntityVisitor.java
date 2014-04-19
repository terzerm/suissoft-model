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
 * Visitor for {@link Entity}.
 *
 * @param <I> type for input passed to visit method
 * @param <R> type for result returned by visit method
 */
public interface EntityVisitor<I, R> {
	R visitAddress(Address address, I input);
	R visitAddressType(AddressType addressType, I input);
	R visitCarrier(Carrier carrier, I input);
	R visitClient(Client client, I input);
	R visitContatInfo(ContactInfo contactInfo, I input);
	R visitContactInfoType(ContactInfoType contactInfoType, I input);
	R visitCountry(Country country, I input);
	R visitJuristicPerson(JuristicPerson juristicPerson, I input);
	R visitNaturalPerson(NaturalPerson naturalPerson, I input);
	R visitOrder(Order order, I input);
	R visitOwnCompany(OwnCompany ownCompany, I input);
	R visitOwnEmployee(OwnEmployee ownEmployee, I input);
	R visitProduct(Product product, I input);
	R visitProductCategory(ProductCategory productCategory, I input);
	R visitQuote(Quote quote, I input);
	R visitRelationship(Relationship relationship, I input);
	R visitRelationshipType(RelationshipType relationshipType, I input);
	R visitServiceProvider(ServiceProvider serviceProvider, I input);
	R visitSupplier(Supplier supplier, I input);
}
