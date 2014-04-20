package com.suissoft.model.dao.partner;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.runner.RunWith;

import com.suissoft.model.dao.Dao;
import com.suissoft.model.dao.partner.JuristicPersonDaoImpl;
import com.suissoft.model.dao.partner.NaturalPersonDaoImpl;
import com.suissoft.model.entity.partner.NaturalPerson;
import com.suissoft.model.inject.cdi.DaoProducer;
import com.suissoft.model.inject.cdi.EntityManagerProducer;

/**
 * Unit test for {@link Dao} for {@link NaturalPerson}
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({EntityManagerProducer.class, DaoProducer.class, NaturalPersonDaoImpl.class, JuristicPersonDaoImpl.class})
public class NaturalPersonDaoCdiTest extends AbstractNaturalPersonDaoTest {

}
