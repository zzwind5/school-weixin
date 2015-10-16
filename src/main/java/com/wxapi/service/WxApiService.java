package com.wxapi.service;

import com.wxapi.message.WxMessageBase;
import com.wxapi.vo.WxApiResultVo;
import com.wxapi.vo.WxTokenValidateVo;

public interface WxApiService {

	String validateToken(WxTokenValidateVo tokenVo);
	
	WxMessageBase wxMessageHandle(String requestMsg, String ownerName);
	
	WxApiResultVo publishMenu(String ownerName);
}
