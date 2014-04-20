package com.suissoft.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.partner.JuristicPersonDao;
import com.suissoft.model.dao.partner.JuristicPersonDaoImpl;
import com.suissoft.model.dao.partner.NaturalPersonDao;
import com.suissoft.model.dao.partner.NaturalPersonDaoImpl;
import com.suissoft.model.entity.Entity;
import com.suissoft.model.entity.partner.Country;
import com.suissoft.model.entity.partner.JuristicPerson;
import com.suissoft.model.entity.partner.NaturalPerson;
import com.suissoft.model.entity.partner.Partner;

/**
 * Unit test for {@link GenericsUtil}
 */
public class GenericsUtilTest {
	
	@SuppressWarnings("unused")
	private Dao<Country> daoCountry;
	
	private static interface DaoPartner extends Dao<Partner> {}
	
	@SuppressWarnings("unused")
	private Dao<? extends Entity> daoUnknown;

	@Test
	public void shouldFindNaturalPersonDaoEntityType() {
		assertEquals(NaturalPerson.class, GenericsUtil.getTypeArgument(NaturalPersonDao.class, Dao.class.getTypeParameters()[0]));
		assertEquals(NaturalPerson.class, GenericsUtil.getTypeArgument(NaturalPersonDaoImpl.class, Dao.class.getTypeParameters()[0]));
	}
	@Test
	public void shouldFindJuristicPersonDaoEntityType() {
		assertEquals(JuristicPerson.class, GenericsUtil.getTypeArgument(JuristicPersonDao.class, Dao.class.getTypeParameters()[0]));
		assertEquals(JuristicPerson.class, GenericsUtil.getTypeArgument(JuristicPersonDaoImpl.class, Dao.class.getTypeParameters()[0]));
	}
	@Test
	public void shouldFindCountryDaoEntityType() throws NoSuchFieldException, SecurityException {
		assertEquals(Country.class, GenericsUtil.getTypeArgument(GenericsUtilTest.class.getDeclaredField("daoCountry").getGenericType(), Dao.class.getTypeParameters()[0]));
	}
	@Test
	public void shouldFindDaoPartnerEntityType() {
		assertEquals(Partner.class, GenericsUtil.getTypeArgument(DaoPartner.class, Dao.class.getTypeParameters()[0]));
	}
	@Test
	public void shouldNotFindUnknownDaoEntityType() throws NoSuchFieldException, SecurityException {
		assertNull(GenericsUtil.getTypeArgument(GenericsUtilTest.class.getDeclaredField("daoUnknown").getGenericType(), Dao.class.getTypeParameters()[0]));
	}
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForIllegalGenericType() throws NoSuchFieldException, SecurityException {
		GenericsUtil.getTypeArgument(Dao.class.getTypeParameters()[0], Dao.class.getTypeParameters()[0]);
	}
}
