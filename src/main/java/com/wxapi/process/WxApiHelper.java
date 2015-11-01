package com.wxapi.process;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wxapi.cache.WxOwnerCache;
import com.wxapi.model.WxOwner;
import com.wxapi.vo.WxAccessTokenVo;
import com.wxapi.vo.WxApiResultVo;

@Component
public class WxApiHelper {
	
	@Autowired private WxOwnerCache ownerCache;
	
	//token 接口
	private static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	private static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	private static final String FETCH_VOICE = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
	
	//Qiniu interface
	private static final String QINIU_FILE_DETAIL = "http://api.qiniu.com/status/get/prefop?id=%s";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public WxAccessTokenVo getWxAccessTokenVo(WxOwner wxOwner) {
		String url = String.format(TOKEN, wxOwner.getAppId(), wxOwner.getAppSecret());
		return restTemplate.getForObject(url, WxAccessTokenVo.class);
	}
	
	//发布菜单
	public WxApiResultVo publishMenus(WxOwner wxOwner) {
		String url = String.format(MENU_CREATE, wxOwner.getWxAccessToken());
		return restTemplate.postForObject(url, wxOwner.getMenu().getBytes(), WxApiResultVo.class);
	}
	
	public String getMediaFetchUrl(String mediaId, Long ownerId) {
		WxOwner wxOwner = ownerCache.getWxOwner(ownerId);
		return String.format(FETCH_VOICE, wxOwner.getWxAccessToken(), mediaId);
	}
	
	public JSONObject getQiniuFileDetail(String fileId) {
		String url = String.format(QINIU_FILE_DETAIL, fileId);
		String reStr = restTemplate.getForObject(url, String.class);
		return JSONObject.fromObject(reStr);
	}
}
