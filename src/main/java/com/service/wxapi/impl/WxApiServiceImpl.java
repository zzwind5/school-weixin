package com.service.wxapi.impl;

import org.springframework.stereotype.Component;

import com.data.vo.wxapi.WxTokenValidateVo;
import com.service.wxapi.WxApiService;
import com.util.wxapi.SignUtil;

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
