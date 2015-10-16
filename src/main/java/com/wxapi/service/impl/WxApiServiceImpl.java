package com.wxapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.util.JsonUtil;
import com.wxapi.cache.WxOwnerCache;
import com.wxapi.cache.WxWorkflowCtxCache;
import com.wxapi.message.WxEventType;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMessageEvent;
import com.wxapi.model.WxOwner;
import com.wxapi.process.WxApiHelper;
import com.wxapi.service.WxApiService;
import com.wxapi.util.SignUtil;
import com.wxapi.vo.WxApiResultVo;
import com.wxapi.vo.WxTokenValidateVo;
import com.wxapi.workflow.WxWorkFlowAction;
import com.wxapi.workflow.WxWorkflowCtx;
import com.wxapi.workflow.WxWorkflowManager;

@Component
public class WxApiServiceImpl implements WxApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(WxApiServiceImpl.class);
	
	@Autowired
	private WxOwnerCache ownerCache;
	
	@Autowired
	private WxApiHelper wxApiHelper;
	
	@Autowired
	private WxWorkflowCtxCache workFlowCache;
	
	@Autowired
	private WxWorkflowManager workFlowManager;

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

	@Override
	public WxMessageBase wxMessageHandle(String requestMsg, String ownerName) {
		WxOwner wxOwner = ownerCache.getWxOwner(ownerName);
		WxMessageBase messageBase = JsonUtil.xmlToObject(requestMsg, WxMessageBase.class);
		messageBase.setOwnerId(wxOwner.getId());
		
		WxMessageBase responseMsg = cachedWorkFlowHandle(messageBase);
		if (responseMsg != null) {
			return responseMsg;
		}
		
		return null;
	}
	
	/**
	 * 对微信 事件的处理，比如菜单点击事件
	 * @param msgEvent
	 * @return
	 */
	private String wxMessageEventHandle(WxMessageEvent msgEvent) {
		if (msgEvent.getEvent() == WxEventType.CLICK) {
			return clickEventHandle(msgEvent);
		}
		return null;
	}
	
	private WxMessageBase cachedWorkFlowHandle(WxMessageBase messageBase) {
		WxWorkflowCtx workFlowCtx = workFlowCache.getWxWorkflowCtx(messageBase.getFromUserName(), messageBase.getOwnerId());
		if (workFlowCtx ==  null) {
			//Not cached.
			return null;
		}
		
		WxWorkFlowAction wfAction = workFlowManager.getWorkFlowAction(workFlowCtx.getWorkFlowActionKey());
		int nextStepIndex = workFlowCtx.getNextStepIndex();
		if (!wfAction.isNextStepMatch(nextStepIndex, messageBase)) {
			return null;
		}
		
		return wfAction.process(messageBase);
	}
	
	private String clickEventHandle(WxMessageEvent msgEvent) {
		
		return null;
	}

}
