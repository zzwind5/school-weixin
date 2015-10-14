package com.wxapi.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wxapi.process.MsgXmlUtil;
import com.wxapi.service.WxApiService;
import com.wxapi.vo.MsgRequest;
import com.wxapi.vo.WxApiResultVo;
import com.wxapi.vo.WxTokenValidateVo;
import com.wxcms.domain.Account;

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
	
	/**
	 * POST 请求：进行消息处理；
	 * */
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public @ResponseBody String doPost(HttpServletRequest request,@PathVariable String account,HttpServletResponse response) {
		//处理用户和微信公众账号交互消息
		Account accountObj = myService.getByAccount(account);//获取account
		try {
			MsgRequest msgRequest = MsgXmlUtil.parseXml(request);//获取发送的消息
			return myService.processMsg(msgRequest,accountObj);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
