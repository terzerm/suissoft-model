package com.suissoft.model.visitor;

import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;

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
import com.suissoft.model.entity.product.Order;
import com.suissoft.model.entity.product.Product;
import com.suissoft.model.entity.product.ProductCategory;
import com.suissoft.model.entity.product.Quote;

public class EntityVisitorTest {

	private static final Entity[] ENTITIES = new Entity[] { new Address(), new AddressType(), new Carrier(), new Client(), new ContactInfo(), new ContactInfoType(), new Country(), new Order(), new OwnCompany(), new OwnEmployee(), new NaturalPerson(), new JuristicPerson(), new Product(), new ProductCategory(), new Quote(), new Relationship(), new RelationshipType(), new ServiceProvider(), new Supplier() };

	@Test
	public void shouldAcceptEntityAdapter() {
		//given
		final EntityVisitor.Adapter<Integer, String> adapterVisitor = new EntityVisitor.Adapter<Integer, String>() {};

		for (int i = 0; i < ENTITIES.length; i++) {
			//when
			final String result = ENTITIES[i].accept(adapterVisitor, i * i * i);

			//then
			assertNull(ENTITIES[i].toString(), result);
		}
	}

	static int getVisitMethodCount(Class<?> visitorClass) {
		int count = 0;
		for (final Method method : visitorClass.getMethods()) {
			if (method.getName().startsWith("visit")) {
				count++;
			}
		}
		return count;
	}

}
