package com.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class WxMessageUtilTest {

	@Test
	public void messageTest(){
		String msgKey = "wxapi.service.welcome";
		Object[] params = {"01机械2班"};
		String resMsg = WxMessageUtil.getMessage(msgKey, params);
		System.out.println(resMsg);
	}
}
