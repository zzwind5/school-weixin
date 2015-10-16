package com.wxapi.workflow;

import com.wxapi.message.WxMessageBase;

public interface WxWorkFlowAction {
	
	void register();
	
	boolean isActionMatch(WxMessageBase message);
	
	WxMessageBase process(WxMessageBase message);
}
