package com.wxapi.service.impl;

import org.springframework.stereotype.Component;

import com.wxapi.service.WxApiService;
import com.wxapi.util.SignUtil;
import com.wxapi.vo.WxTokenValidateVo;

@Component
public class WxApiServiceImpl implements WxApiService {
	
	private static final String TOKEN = "ycwgy";

	@Override
	public String validateToken(WxTokenValidateVo tokenVo) {
		if (SignUtil.validSign(tokenVo.getSignature(), TOKEN, tokenVo.getTimestamp(), tokenVo.getNonce())) {
			return tokenVo.getEchostr();
		} else {
			return "error";
		}
	}

}
