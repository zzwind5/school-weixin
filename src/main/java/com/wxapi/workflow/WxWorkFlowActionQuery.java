package com.wxapi.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxapi.cache.WxHomeWorkCache;
import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxEventType;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMessageEvent;

@Component
public class WxWorkFlowActionQuery extends WxWorkFlowActionAbstract {
	
	@Autowired
	private WxHomeWorkCache homeWorkCache;

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
		if (message instanceof WxMessageEvent) {
			return homeWorkCache.queryHomeWork((WxMessageEvent)message);
		}
		
		return null;
	}

}
