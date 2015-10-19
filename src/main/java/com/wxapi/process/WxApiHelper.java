package com.wxapi.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wxapi.model.WxOwner;
import com.wxapi.vo.WxAccessTokenVo;
import com.wxapi.vo.WxApiResultVo;

@Component
public class WxApiHelper {
	
	//token 接口
	private static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	private static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public WxAccessTokenVo getWxAccessTokenVo(WxOwner wxOwner) {
		String url = String.format(TOKEN, wxOwner.getAppId(), wxOwner.getAppSecret());
		return restTemplate.getForObject(url, WxAccessTokenVo.class);
	}
	
	//发布菜单
	public WxApiResultVo publishMenus(WxOwner wxOwner) {
		String token = getWxAccessTokenVo(wxOwner).getAccessToken();
		String url = String.format(MENU_CREATE, token);
		return restTemplate.postForObject(url, wxOwner.getMenu().getBytes(), WxApiResultVo.class);
	}
}
