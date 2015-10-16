package com.wxapi.workflow;

import org.springframework.beans.factory.annotation.Autowired;

import com.wxapi.cache.WxWorkflowCtxCache;
import com.wxapi.message.WxMessageBase;

public abstract class WxWorkFlowActionAbstract implements WxWorkFlowAction {
	
	@Autowired
	private WxWorkflowManager workFlowManager;
	
	@Autowired
	protected WxWorkflowCtxCache workFlowCtxCache;

	WxWorkFlowActionAbstract(){
		workFlowManager.register(this);
	}
	
	@Override
	public WxWorkflowCtx startWorkFlow(WxMessageBase message) {
		WxWorkflowCtx wfCtx = new WxWorkflowCtx(message);
		wfCtx.setSteps(this.getStepCount());
		wfCtx.setWorkFlowActionKey(this.getClass().getSimpleName());
		workFlowCtxCache.cacheWxWorkflowCtx(wfCtx);
		return wfCtx;
	}
	
	@Override
	public WxMessageBase process(WxMessageBase message) {
		WxWorkflowCtx workFlowCtx = workFlowCtxCache.getWxWorkflowCtx(message.getFromUserName(), message.getOwnerId());
		workFlowCtx.cache(message);
		WxMessageBase responseMsg = getResponseMsg(workFlowCtx.getLatestStepIndex(), message.getFromUserName(), message.getOwnerId());
		
		if (workFlowCtx.isFinished()) {
			closeWorkFlow(workFlowCtx);
		}
		
		return responseMsg;
	}
}
