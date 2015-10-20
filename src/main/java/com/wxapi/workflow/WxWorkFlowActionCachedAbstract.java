package com.wxapi.workflow;

import org.springframework.beans.factory.annotation.Autowired;

import com.wxapi.cache.WxWorkflowCtxCache;
import com.wxapi.message.WxMessageBase;

public abstract class WxWorkFlowActionCachedAbstract extends
		WxWorkFlowActionAbstract implements WxWorkFlowActionCached {

	@Autowired
	private WxWorkflowManager workFlowManager;

	@Autowired
	protected WxWorkflowCtxCache workFlowCtxCache;

	@Override
	public boolean isActionMatch(WxMessageBase message) {
		return isNextStepMatch(0, message);
	}

	@Override
	public WxMessageBase startCachedWorkFlow(WxMessageBase message) {
		WxWorkflowCtx wfCtx = new WxWorkflowCtx(message);
		wfCtx.setSteps(this.getStepCount());
		wfCtx.setWorkFlowActionKey(this.getClass().getSimpleName());
		workFlowCtxCache.cacheWxWorkflowCtx(wfCtx);

		return process(message);
	}

	@Override
	public WxMessageBase process(WxMessageBase message) {
		WxWorkflowCtx workFlowCtx = workFlowCtxCache.getWxWorkflowCtx(
				message.getFromUserName(), message.getOwnerId());
		workFlowCtx.cache(message);
		WxMessageBase responseMsg = getResponseMsg(
				workFlowCtx.getLatestStepIndex(), message.getFromUserName(),
				message.getOwnerId());

		if (workFlowCtx.isFinished()) {
			closeCachedWorkFlow(workFlowCtx);
		}

		return responseMsg;
	}
}
