package com.suissoft.model.inject.cdi;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.partner.JuristicPersonDao;
import com.suissoft.model.dao.partner.JuristicPersonDaoImpl;
import com.suissoft.model.dao.partner.NaturalPersonDao;
import com.suissoft.model.dao.partner.NaturalPersonDaoImpl;
import com.suissoft.model.entity.partner.Country;
import com.suissoft.model.entity.partner.JuristicPerson;
import com.suissoft.model.entity.partner.NaturalPerson;
import com.suissoft.model.inject.cdi.DaoProducer;

/**
 * Unit test for {@link DaoProducer} and other injected specialised {@link Dao}s.
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({EntityManagerProducer.class, DaoProducer.class, NaturalPersonDaoImpl.class, JuristicPersonDaoImpl.class})
public class DaoInjectionTest {
	
	@Inject 
	private NaturalPersonDao naturalPersonDao;
	@Inject 
	private Dao<NaturalPerson> daoNaturalPerson;
	@Inject 
	private JuristicPersonDao juristicPersonDao;
	@Inject
	private Dao<JuristicPerson> daoJuristicPerson;
	@Inject 
	private Dao<Country> daoCountry;
	
	@Test
	public void shouldInjectCountryDao() {
		assertThat(daoCountry, is(instanceOf(Dao.class)));
		assertEquals("dao entity class should be Country", Country.class, daoCountry.getEntityClass());
	}
	@Test
	public void shouldInjectNaturalPersonDao() {
		assertThat(naturalPersonDao, is(instanceOf(NaturalPersonDao.class)));
		assertEquals("dao entity class should be NaturalPerson", NaturalPerson.class, naturalPersonDao.getEntityClass());
		assertThat(daoNaturalPerson, is(instanceOf(Dao.class)));
	}
	@Test
	public void shouldInjectJuristicPersonDao() {
		assertThat(juristicPersonDao, is(instanceOf(JuristicPersonDao.class)));
		assertEquals("dao entity class should be JuristicPerson", JuristicPerson.class, juristicPersonDao.getEntityClass());
		assertThat(daoJuristicPerson, is(instanceOf(Dao.class)));
	}
}
