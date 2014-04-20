package com.suissoft.model.dao.partner;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.runner.RunWith;

import com.suissoft.model.dao.partner.JuristicPersonDaoImpl;
import com.suissoft.model.dao.partner.NaturalPersonDaoImpl;
import com.suissoft.model.inject.cdi.DaoProducer;
import com.suissoft.model.inject.cdi.EntityManagerProducer;

@RunWith(CdiRunner.class)
@AdditionalClasses({EntityManagerProducer.class, DaoProducer.class, NaturalPersonDaoImpl.class, JuristicPersonDaoImpl.class})
public class JuristicPersonDaoCdiTest extends AbstractJuristicPersonDaoTest {


}
