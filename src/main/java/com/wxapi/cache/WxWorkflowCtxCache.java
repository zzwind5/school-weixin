package com.wxapi.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.wxapi.workflow.WxWorkflowCtx;

@Component
public class WxWorkflowCtxCache {

	private Map<String, WxWorkflowCtx> cacheMap = new ConcurrentHashMap<>();
	
	public void cacheWxWorkflowCtx(WxWorkflowCtx workFlowCtx) {
		cacheMap.put(workFlowCtx.getWorkFlowCtxKey(), workFlowCtx);
	}
	
	public void clearWxWorkflowCtx(WxWorkflowCtx workFlowCtx) {
		cacheMap.remove(workFlowCtx.getWorkFlowCtxKey());
	}
	
	public WxWorkflowCtx getWxWorkflowCtx(String wxUserName, Long ownerId) {
		String key = WxWorkflowCtx.generateKey(wxUserName, ownerId);
		return cacheMap.get(key);
	}
}
