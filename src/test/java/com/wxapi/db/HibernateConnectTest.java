package com.wxapi.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class HibernateConnectTest {
	
//	@Autowired
//	private HibernateTemplate hTemplate;
	
	@Autowired
	private LocalSessionFactoryBean session;

	@Test
	public void connectionTest(){
		System.out.println(session.getHibernateProperties());
//		User user = hTemplate.get(User.class, 1);
//		System.out.println(user);
	}
}
