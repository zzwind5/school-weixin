package com.wxapi.service;

import com.wxapi.vo.WxTokenValidateVo;

public interface WxApiService {

	String validateToken(WxTokenValidateVo tokenVo);
}
