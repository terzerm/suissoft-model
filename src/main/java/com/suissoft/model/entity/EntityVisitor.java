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
