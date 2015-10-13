package com.wxapi.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wxapi.service.WxApiService;
import com.wxapi.vo.WxApiResultVo;
import com.wxapi.vo.WxTokenValidateVo;

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
	
	//创建微信公众账号菜单
	@RequestMapping(value = "/publishMenu")
	@ResponseBody
	public String publishMenu(String ownerName) {
		WxApiResultVo resVo = wxApiService.publishMenu(ownerName);
		return resVo.getErrmsg();
	}
}
