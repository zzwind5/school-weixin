package com.wxapi.workflow;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxEventType;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMessageEvent;
import com.wxapi.message.WxMessageText;

public abstract class WxWorkFlowActionAbstract implements WxWorkFlowAction {

	@Autowired
	private WxWorkflowManager workFlowManager;
	
	@PostConstruct
	public void register(){
		workFlowManager.register(this);
	}
	
	public WxEventOperation getOperationEnum(WxMessageBase message) {
		WxEventOperation queryOpt = null;
		if (message instanceof WxMessageEvent) {
			WxMessageEvent msgEvent = (WxMessageEvent)message;
			queryOpt = msgEvent.getEvent() == WxEventType.CLICK ? msgEvent.getEventKey() : null;
		} else if (message instanceof WxMessageText) {
			queryOpt = WxEventOperation.getEnumByKey(((WxMessageText)message).getContent());
		}
		
		return queryOpt;
	}
}
