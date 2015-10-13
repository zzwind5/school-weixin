package com.wxapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxapi.cache.WxOwnerCache;
import com.wxapi.model.WxOwner;
import com.wxapi.process.WxApiHelper;
import com.wxapi.service.WxApiService;
import com.wxapi.util.SignUtil;
import com.wxapi.vo.WxApiResultVo;
import com.wxapi.vo.WxTokenValidateVo;

@Component
public class WxApiServiceImpl implements WxApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(WxApiServiceImpl.class);
	
	@Autowired
	private WxOwnerCache ownerCache;
	
	@Autowired
	private WxApiHelper wxApiHelper;

	@Override
	public String validateToken(WxTokenValidateVo tokenVo) {
		WxOwner wxOwner = ownerCache.getWxOwner(tokenVo.getOwnerName());
		if (SignUtil.validSign(tokenVo.getSignature(), wxOwner.getToken(), tokenVo.getTimestamp(), tokenVo.getNonce())) {
			return tokenVo.getEchostr();
		} else {
			return "error";
		}
	}

	@Override
	public WxApiResultVo publishMenu(String ownerName) {
		WxOwner wxOwner = ownerCache.getWxOwner(ownerName);
		WxApiResultVo resVo = wxApiHelper.publishMenus(wxOwner);
		if (resVo.getErrcode() != 0) {
			logger.error(resVo.getErrmsg());
		}
		return resVo;
	}

}
