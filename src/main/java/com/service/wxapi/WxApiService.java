package com.service.wxapi;

import com.data.vo.wxapi.WxTokenValidateVo;

public interface WxApiService {

	String validateToken(WxTokenValidateVo tokenVo);
}
