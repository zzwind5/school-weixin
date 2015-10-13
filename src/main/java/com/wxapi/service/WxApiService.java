package com.wxapi.service;

import com.wxapi.vo.WxApiResultVo;
import com.wxapi.vo.WxTokenValidateVo;

public interface WxApiService {

	String validateToken(WxTokenValidateVo tokenVo);
	
	WxApiResultVo publishMenu(String ownerName);
}
