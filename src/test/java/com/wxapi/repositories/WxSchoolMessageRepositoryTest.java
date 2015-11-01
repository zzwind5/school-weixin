package com.wxapi.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class WxSchoolMessageRepositoryTest {
	
	@Autowired private WxSchoolMessageRepository rep;
	
	@Test
	@Transactional
	public void updateTest(){
		rep.updateExternalUrlById("www.baidu.com", 46l);
	}
}
