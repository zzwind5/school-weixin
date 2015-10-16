package com.wxapi.workflow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class WxWorkflowManager {

	private Map<String, WxWorkFlowAction> registerMap = new HashMap<>();
	
	public void register(WxWorkFlowAction action) {
		registerMap.put(action.getClass().getSimpleName(), action);
	}
	
	public WxWorkFlowAction getWorkFlowAction(String actionKey) {
		return registerMap.get(actionKey);
	}
}
