package com.suissoft.model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

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

public class EntityVisitorTest {

	private static class TestVisitor implements EntityVisitor<Integer, String> {

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

		public String visitOrder(Order order, Integer input) {
			return visit(order, input);
		}

		public String visitOwnCompany(OwnCompany ownCompany, Integer input) {
			return visit(ownCompany, input);
		}

		public String visitOwnEmployee(OwnEmployee ownEmployee, Integer input) {
			return visit(ownEmployee, input);
		}

		public String visitProduct(Product product, Integer input) {
			return visit(product, input);
		}

		public String visitProductCategory(ProductCategory productCategory, Integer input) {
			return visit(productCategory, input);
		}

		public String visitQuote(Quote quote, Integer input) {
			return visit(quote, input);
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

	public static final Entity[] ENTITIES = new Entity[] { new Address(), new AddressType(), new Carrier(), new Client(), new ContactInfo(), new ContactInfoType(), new Country(), new Order(), new OwnCompany(), new OwnEmployee(), new NaturalPerson(), new JuristicPerson(), new Product(), new ProductCategory(), new Quote(), new Relationship(), new RelationshipType(), new ServiceProvider(), new Supplier() };

	@Test
	public void shouldVisitForEveryEntityOnce() {
		//given
		final TestVisitor sharedVisitor = new TestVisitor();
		final EntityVisitorAdapter<Integer, String> adapterVisitor = new EntityVisitorAdapter<>();

		for (int i = 0; i < ENTITIES.length; i++) {
			//given
			final TestVisitor individualVisitor = new TestVisitor();

			//when
			final String result1 = ENTITIES[i].accept(sharedVisitor, i);
			final String result2 = ENTITIES[i].accept(individualVisitor, i * i);
			final String result3 = ENTITIES[i].accept(adapterVisitor, i * i * i);

			//then
			assertEquals(1, sharedVisitor.getCountFor(ENTITIES[i].getClass()));
			assertEquals(1, individualVisitor.getCountFor(ENTITIES[i].getClass()));
			assertEquals(1, individualVisitor.getEntityTypeCount());
			assertEquals(String.valueOf(i), result1);
			assertEquals(String.valueOf(i * i), result2);
			assertNull(result3);
		}

		//then
		assertEquals(getVisitMethodCount(), sharedVisitor.getEntityTypeCount());
	}

	private int getVisitMethodCount() {
		int count = 0;
		for (final Method method : EntityVisitor.class.getMethods()) {
			if (method.getName().startsWith("visit")) {
				count++;
			}
		}
		return count;
	}

}
