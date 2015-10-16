package com.wxapi.workflow;

import org.springframework.stereotype.Component;

import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxEventType;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMessageEvent;
import com.wxapi.message.WxMessageText;

@Component
public class WxWorkFlowActionQuery extends WxWorkFlowActionAbstract {

	@Override
	public boolean isActionMatch(WxMessageBase message) {
		if ( !(message instanceof WxMessageEvent) ) {
			return false;
		}
		
		WxMessageEvent msgEvent = (WxMessageEvent)message;
		return msgEvent.getEvent() == WxEventType.CLICK && 
				(msgEvent.getEventKey() == WxEventOperation.QUERY_CHINESE
					|| msgEvent.getEventKey() == WxEventOperation.QUERY_MATH
					|| msgEvent.getEventKey() == WxEventOperation.QUERY_ENGLISH
					|| msgEvent.getEventKey() == WxEventOperation.QUERY_MESSAGE
					|| msgEvent.getEventKey() == WxEventOperation.QUERY_ALL
				);
	}

	@Override
	public WxMessageBase process(WxMessageBase message) {
		WxMessageText msgText = new WxMessageText();
		msgText.setFromUserName(message.getToUserName());
		msgText.setToUserName(message.getFromUserName());
		msgText.setCreateTime(System.currentTimeMillis());
		msgText.setContent("查询功能开发中");
		
		return msgText;
	}

}
