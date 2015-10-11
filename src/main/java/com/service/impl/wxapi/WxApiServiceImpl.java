package com.service.impl.wxapi;

import org.springframework.stereotype.Component;

import com.data.vo.wxapi.WxTokenValidateVo;
import com.service.Int.wxapi.WxApiService;
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
