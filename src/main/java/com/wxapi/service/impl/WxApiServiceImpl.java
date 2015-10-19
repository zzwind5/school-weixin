package com.wxapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.util.JsonUtil;
import com.wxapi.cache.WxOwnerCache;
import com.wxapi.cache.WxWorkflowCtxCache;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.response.WxMessageResponseNews;
import com.wxapi.message.response.WxMessageNewsItem;
import com.wxapi.model.WxOwner;
import com.wxapi.process.WxApiHelper;
import com.wxapi.service.WxApiService;
import com.wxapi.util.SignUtil;
import com.wxapi.vo.WxApiResultVo;
import com.wxapi.vo.WxTokenValidateVo;
import com.wxapi.workflow.WxWorkFlowAction;
import com.wxapi.workflow.WxWorkFlowActionCached;
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
		
		//Cached event handle, step 2, 3, 4 .....
		WxMessageBase responseMsg = cachedWorkFlowHandle(messageBase);
		if (responseMsg != null) {
			return responseMsg;
		}
		
		//For that no need cache or start a cached work flow.
		responseMsg = firstTimeWorkFlowHandle(messageBase);
		if (responseMsg != null) {
			return responseMsg;
		}
		
		//return a welcome message.
//		responseMsg = createDefaultMessage(messageBase);
		responseMsg = createDefaultMessageNews(messageBase);
		
		return responseMsg;
	}
	
	/** Private function start ***************************************************************/
	
	private WxMessageBase cachedWorkFlowHandle(WxMessageBase messageBase) {
		WxWorkflowCtx workFlowCtx = workFlowCache.getWxWorkflowCtx(messageBase.getFromUserName(), messageBase.getOwnerId());
		if (workFlowCtx ==  null) {
			//Not cached.
			return null;
		}
		
		WxWorkFlowActionCached cachedAction = (WxWorkFlowActionCached)workFlowManager.getWorkFlowAction(workFlowCtx.getWorkFlowActionKey());
		int nextStepIndex = workFlowCtx.getNextStepIndex();
		if (!cachedAction.isNextStepMatch(nextStepIndex, messageBase)) {
			return null;
		}
		
		return cachedAction.process(messageBase);
	}
	
	private WxMessageBase firstTimeWorkFlowHandle(WxMessageBase messageBase) {
		WxWorkFlowAction workFlowAction = workFlowManager.getMatchedWorkFlowAction(messageBase);
		if (workFlowAction == null) {
			//No matched work flow handle.
			return null;
		}
		
		if (workFlowAction instanceof WxWorkFlowActionCached) {
			WxWorkFlowActionCached cachedAction = (WxWorkFlowActionCached)workFlowAction;
			return cachedAction.startCachedWorkFlow(messageBase);
		} else {
			return workFlowAction.process(messageBase);
		}
	}
	
	private WxMessageBase createDefaultMessageNews(WxMessageBase messageBase) {
		WxMessageResponseNews newMsg = new WxMessageResponseNews();
		newMsg.setFromUserName(messageBase.getToUserName());
		newMsg.setToUserName(messageBase.getFromUserName());
		newMsg.setCreateTime(System.currentTimeMillis());
		newMsg.setArticleCount(1);
		newMsg.setFuncFlag(1);
	
		WxMessageNewsItem newsItem1 = new WxMessageNewsItem();
		StringBuffer des = new StringBuffer();
		des.append("语文：拼音快乐读10次");
		des.append("\n");
		des.append("\n");
		des.append("_________________________");
		des.append("\n");
		des.append("\n");
		des.append("\n");
		des.append("数学：10以内加减法");
		
		newsItem1.setDescription(des.toString());
		newsItem1.setTitle("家庭作业");
		newsItem1.setPicUrl("");
//		newsItem1.setPicUrl("http://v1.qzone.cc/avatar/201405/01/01/40/53613582688bd260.jpg%21200x200.jpg");
//		newsItem1.setUrl("111");
		
//		WxMessageNewsItem newsItem2 = new WxMessageNewsItem();
//		newsItem2.setDescription("10以内加减法");
//		newsItem2.setTitle("数学");
//		newsItem2.setPicUrl("http://v1.qzone.cc/avatar/201405/01/01/40/53613582688bd260.jpg%21200x200.jpg");
//		newsItem2.setUrl("111");
		
		newMsg.getArticles().add(newsItem1);
//		newMsg.getArticles().add(newsItem2);
		
		return newMsg;
	}

}
