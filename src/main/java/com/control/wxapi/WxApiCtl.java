package com.control.wxapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.vo.wxapi.WxTokenValidateVo;
import com.service.wxapi.WxApiService;

@Controller
@RequestMapping("/wxapi")
public class WxApiCtl {
	
//	public static final String DOMAIN_NAME = "YCWGY_2015_02";
	
	@Autowired
	private WxApiService wxApiService;

	@ResponseBody
	@RequestMapping(value = "/message",  method = RequestMethod.GET)
	public String doGet(WxTokenValidateVo tokenVo) {
		return wxApiService.validateToken(tokenVo);
	}
}
