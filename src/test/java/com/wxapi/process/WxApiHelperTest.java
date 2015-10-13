package com.wxapi.process;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxapi.cache.WxOwnerCache;
import com.wxapi.model.WxOwner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class WxApiHelperTest {
	
	@Autowired
	private WxApiHelper wxApiHelper;
	
	@Autowired
	private WxOwnerCache ownerCache;
	
	private WxOwner owner;
	
	@Before
	public void init(){
		owner = ownerCache.getWxOwner("YCWGY_2015_02");
	}

	@Test
	public void getWxAccessTokenVo() {
		System.out.println(wxApiHelper.getWxAccessTokenVo(owner));
	}
	
	@Test
	public void publishMenus() throws UnsupportedEncodingException{
		System.out.println(wxApiHelper.publishMenus(owner));
	}
}
