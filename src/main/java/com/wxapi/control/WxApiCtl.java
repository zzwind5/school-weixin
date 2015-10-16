package com.wxapi.control;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.JsonUtil;
import com.wxapi.message.WxMessageBase;
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
	
	/**
	 * POST 请求：进行消息处理；
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public @ResponseBody String doPost(@RequestBody String requestMsg,
			@RequestParam String ownerName) {
		
		WxMessageBase msgBase = wxApiService.wxMessageHandle(requestMsg, ownerName);
		return JsonUtil.toXmlString(msgBase, true);
	}
	
	//创建微信公众账号菜单
	@RequestMapping(value = "/publishMenu")
	@ResponseBody
	public String publishMenu(String ownerName) {
		WxApiResultVo resVo = wxApiService.publishMenu(ownerName);
		return resVo.getErrmsg();
	}
}
