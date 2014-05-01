package com.suissoft.model.visitor;

import com.suissoft.model.entity.Entity;
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

/**
 * Visitor for partner {@link Entity}.
 *
 * @param <I> type for input passed to visit method
 * @param <R> type for result returned by visit method
 */
public interface PartnerVisitor<I, R> {
	default R visitAddress(Address address, I input) {return null;}
	default R visitAddressType(AddressType addressType, I input) {return null;}
	default R visitCarrier(Carrier carrier, I input) {return null;}
	default R visitClient(Client client, I input) {return null;}
	default R visitContatInfo(ContactInfo contactInfo, I input) {return null;}
	default R visitContactInfoType(ContactInfoType contactInfoType, I input) {return null;}
	default R visitCountry(Country country, I input) {return null;}
	default R visitJuristicPerson(JuristicPerson juristicPerson, I input) {return null;}
	default R visitNaturalPerson(NaturalPerson naturalPerson, I input) {return null;}
	default R visitOwnCompany(OwnCompany ownCompany, I input) {return null;}
	default R visitOwnEmployee(OwnEmployee ownEmployee, I input) {return null;}
	default R visitRelationship(Relationship relationship, I input) {return null;}
	default R visitRelationshipType(RelationshipType relationshipType, I input) {return null;}
	default R visitServiceProvider(ServiceProvider serviceProvider, I input) {return null;}
	default R visitSupplier(Supplier supplier, I input) {return null;}
	class Adapter<I, R> implements EntityVisitor<I, R> {
		private final PartnerVisitor<I, R> wrapped;
		public Adapter(PartnerVisitor<I, R> wrapped) {
			this.wrapped = wrapped;
		}
		public R visitAddress(Address address, I input) {return wrapped.visitAddress(address, input);}
		public R visitAddressType(AddressType addressType, I input) {return wrapped.visitAddressType(addressType, input);}
		public R visitCarrier(Carrier carrier, I input) {return wrapped.visitCarrier(carrier, input);}
		public R visitClient(Client client, I input) {return wrapped.visitClient(client, input);}
		public R visitContatInfo(ContactInfo contactInfo, I input) {return wrapped.visitContatInfo(contactInfo, input);}
		public R visitContactInfoType(ContactInfoType contactInfoType, I input) {return wrapped.visitContactInfoType(contactInfoType, input);}
		public R visitCountry(Country country, I input) {return wrapped.visitCountry(country, input);}
		public R visitJuristicPerson(JuristicPerson juristicPerson, I input) {return wrapped.visitJuristicPerson(juristicPerson, input);}
		public R visitNaturalPerson(NaturalPerson naturalPerson, I input) {return wrapped.visitNaturalPerson(naturalPerson, input);}
		public R visitOwnCompany(OwnCompany ownCompany, I input) {return wrapped.visitOwnCompany(ownCompany, input);}
		public R visitOwnEmployee(OwnEmployee ownEmployee, I input) {return wrapped.visitOwnEmployee(ownEmployee, input);}
		public R visitRelationship(Relationship relationship, I input) {return wrapped.visitRelationship(relationship, input);}
		public R visitRelationshipType(RelationshipType relationshipType, I input) {return wrapped.visitRelationshipType(relationshipType, input);}
		public R visitServiceProvider(ServiceProvider serviceProvider, I input) {return wrapped.visitServiceProvider(serviceProvider, input);}
		public R visitSupplier(Supplier supplier, I input) {return wrapped.visitSupplier(supplier, input);}
	}
}
