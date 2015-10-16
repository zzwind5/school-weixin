package com.wxapi.workflow;

import com.wxapi.message.WxMessageBase;

public interface WxWorkFlowAction {
	
	boolean needStartWorkFlow(WxMessageBase message);
	
	int getStepCount();
	
	WxWorkflowCtx startWorkFlow(WxMessageBase message);
	
	void closeWorkFlow(WxWorkflowCtx workFlowCtx);
	
	boolean isNextStepMatch(int nextStepIdx, WxMessageBase message);
	
	WxMessageBase process(WxMessageBase message);
	
	WxMessageBase getResponseMsg(int stepIndex, String fromUserName, Long ownerId);
}
