package com.wxapi.workflow;

import com.wxapi.message.WxMessageBase;

public interface WxWorkFlowActionCached extends WxWorkFlowAction {
	
	int getStepCount();
	
	boolean isNextStepMatch(int nextStepIdx, WxMessageBase message);
	
	WxMessageBase startCachedWorkFlow(WxMessageBase message);
	
	void closeCachedWorkFlow(WxWorkflowCtx workFlowCtx);
	
	WxMessageBase getResponseMsg(int stepIndex, String fromUserName, Long ownerId);
}
