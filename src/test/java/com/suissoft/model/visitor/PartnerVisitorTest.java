package com.suissoft.model.visitor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.junit.Test;

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

public class PartnerVisitorTest {

	static final Entity[] ENTITIES = { new Address(), new AddressType(), new Carrier(), new Client(), new ContactInfo(), new ContactInfoType(), new Country(), new OwnCompany(), new OwnEmployee(), new NaturalPerson(), new JuristicPerson(), new Relationship(), new RelationshipType(), new ServiceProvider(), new Supplier() };

	private static class TestVisitor implements PartnerVisitor<Integer, String> {

		private Map<Class<?>, List<Object>> entityByType = new HashMap<Class<?>, List<Object>>();

		private String visit(Object entity, Integer input) {
			Objects.requireNonNull(entity, "entity cannot be null");
			List<Object> entities = entityByType.get(entity.getClass());
			if (entities == null) {
				entities = new ArrayList<>();
				entityByType.put(entity.getClass(), entities);
			}
			entities.add(entity);
			return input == null ? null : input.toString();
		}

		public int getEntityTypeCount() {
			return entityByType.size();
		}

		public int getCountFor(Class<?> entityClass) {
			final List<Object> entities = entityByType.get(entityClass);
			return entities == null ? 0 : entities.size();
		}

		public String visitAddress(Address address, Integer input) {
			return visit(address, input);
		}

		public String visitAddressType(AddressType addressType, Integer input) {
			return visit(addressType, input);
		}

		public String visitCarrier(Carrier carrier, Integer input) {
			return visit(carrier, input);
		}

		public String visitClient(Client client, Integer input) {
			return visit(client, input);
		}

		public String visitContatInfo(ContactInfo contactInfo, Integer input) {
			return visit(contactInfo, input);
		}

		public String visitContactInfoType(ContactInfoType contactInfoType, Integer input) {
			return visit(contactInfoType, input);
		}

		public String visitCountry(Country country, Integer input) {
			return visit(country, input);
		}

		public String visitJuristicPerson(JuristicPerson juristicPerson, Integer input) {
			return visit(juristicPerson, input);
		}

		public String visitNaturalPerson(NaturalPerson naturalPerson, Integer input) {
			return visit(naturalPerson, input);
		}

		public String visitOwnCompany(OwnCompany ownCompany, Integer input) {
			return visit(ownCompany, input);
		}

		public String visitOwnEmployee(OwnEmployee ownEmployee, Integer input) {
			return visit(ownEmployee, input);
		}

		public String visitRelationship(Relationship relationship, Integer input) {
			return visit(relationship, input);
		}

		public String visitRelationshipType(RelationshipType relationshipType, Integer input) {
			return visit(relationshipType, input);
		}

		public String visitServiceProvider(ServiceProvider serviceProvider, Integer input) {
			return visit(serviceProvider, input);
		}

		public String visitSupplier(Supplier supplier, Integer input) {
			return visit(supplier, input);
		}
	}

	@Test
	public void shouldVisitForEveryEntityOnce() {
		//given
		final Random rnd = new Random();
		final int visitMethodCount = VisitorTestUtil.getVisitMethodCount(PartnerVisitor.class);
		final TestVisitor sharedVisitor = new TestVisitor();
		assertEquals(visitMethodCount, ENTITIES.length);

		for (final Entity entity : ENTITIES) {
			//given
			final Integer input1 = rnd.nextInt();
			final Integer input2 = rnd.nextInt();
			final TestVisitor individualVisitor = new TestVisitor();

			//when
			final String result1 = entity.accept(sharedVisitor, input1);
			final String result2 = entity.accept(individualVisitor, input2);

			//then
			assertEquals(entity.toString(), 1, sharedVisitor.getCountFor(entity.getClass()));
			assertEquals(entity.toString(), 1, individualVisitor.getCountFor(entity.getClass()));
			assertEquals(entity.toString(), 1, individualVisitor.getEntityTypeCount());
			assertEquals(entity.toString(), input1.toString(), result1);
			assertEquals(entity.toString(), input2.toString(), result2);
		}

		//then
		assertEquals(visitMethodCount, sharedVisitor.getEntityTypeCount());
	}

}
