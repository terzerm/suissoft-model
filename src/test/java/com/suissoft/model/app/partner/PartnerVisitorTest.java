package com.suissoft.model.app.partner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suissoft.model.app.partner.PartnerVisitor;
import com.suissoft.model.app.partner.entity.JuristicPerson;
import com.suissoft.model.app.partner.entity.NaturalPerson;
import com.suissoft.model.app.partner.entity.Partner;

public class PartnerVisitorTest {

	private static class TestVisitor implements PartnerVisitor<Integer, String> {
		
		private int countNaturalPerson = 0;
		private int countJuristicPerson = 0;
		
		@Override
		public String visitNaturalPerson(NaturalPerson p, Integer input) {
			countNaturalPerson++;
			return null;
		}
		
		@Override
		public String visitJuristicPerson(JuristicPerson p, Integer input) {
			countJuristicPerson++;
			return null;
		}
		
		public int getCountNaturalPerson() {
			return countNaturalPerson;
		}
		
		public int getCountJuristicPerson() {
			return countJuristicPerson;
		}
	}
	
	private TestVisitor testVisitor;
	
	@Before
	public void setup() {
		testVisitor = new TestVisitor();
	}
	
	@Test
	public void shouldVisitNaturalPerson() {
		Partner naturalPerson = new NaturalPerson();
		naturalPerson.accept(testVisitor, 1);

		assertEquals(1, testVisitor.getCountNaturalPerson());
		assertEquals(0, testVisitor.getCountJuristicPerson());
	}

	@Test
	public void shouldVisitJuristicPerson() {
		Partner juristicPerson = new JuristicPerson();
		juristicPerson.accept(testVisitor, 1);

		assertEquals(0, testVisitor.getCountNaturalPerson());
		assertEquals(1, testVisitor.getCountJuristicPerson());
	}
}
