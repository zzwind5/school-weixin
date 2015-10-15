package com.wxapi.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxapi.model.WxOwner;
import com.wxapi.repositories.WxOwnerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class HibernateConnectTest {
	
//	@Autowired
//	private UserReposity userReposity;
	
	@Autowired
	private WxOwnerRepository wxOwnerRep;
//	
//	@Autowired
//	private BasicDataSource dataSource;

//	@Test
//	public void connectionTest(){
//		WxOwner owner = wxOwnerRep.findOne(1l);
//		System.out.println(owner);
//	}
	
	@Test
	public void addData(){
		WxOwner owner = new WxOwner();
		owner.setOwnerName("YCWGY_2015_02");
		owner.setAppId("wxa584a4fc4524fb16");
		owner.setAppSecret("92bb7b53397be2cb0677971a26d5f011");
		owner.setToken("ycwgy");
		owner.setDescription("育才外国语小学2015届2班");
		wxOwnerRep.saveAndFlush(owner);
	}
}
