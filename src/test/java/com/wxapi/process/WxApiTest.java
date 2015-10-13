package com.wxapi.process;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.wxapi.model.WxOwner;
import com.wxapi.repositories.WxOwnerRepository;
import com.wxapi.vo.WxAccessTokenVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class WxApiTest {
	
	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	@Autowired
	private WxOwnerRepository wxOwnerRep;
	
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void accessTokenTest(){
		WxOwner wxOwner = wxOwnerRep.findOne(1l);
		String url = String.format(TOKEN_URL, wxOwner.getAppId(), wxOwner.getAppSecret());
		System.out.println(url);
		WxAccessTokenVo tokenVo = restTemplate.getForObject(url, WxAccessTokenVo.class);
		System.out.println(tokenVo);
	}
}
