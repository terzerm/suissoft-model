package com.suissoft.model.app.partner.dao;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.runner.RunWith;

import com.suissoft.model.app.partner.dao.impl.JuristicPersonDaoImpl;
import com.suissoft.model.app.partner.dao.impl.NaturalPersonDaoImpl;
import com.suissoft.model.cdi.DaoProducer;
import com.suissoft.model.cdi.EntityManagerProducer;

@RunWith(CdiRunner.class)
@AdditionalClasses({EntityManagerProducer.class, DaoProducer.class, NaturalPersonDaoImpl.class, JuristicPersonDaoImpl.class})
public class JuristicPersonDaoCdiTest extends AbstractJuristicPersonDaoTest {


}
