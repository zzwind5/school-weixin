package com.wxapi.workflow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wxapi.message.WxMessageBase;

@Component
public class WxWorkflowManager {

	private Map<String, WxWorkFlowAction> registerMap = new HashMap<>();
	
	public void register(WxWorkFlowAction action) {
		registerMap.put(action.getClass().getSimpleName(), action);
	}
	
	public WxWorkFlowAction getWorkFlowAction(String actionKey) {
		return registerMap.get(actionKey);
	}
	
	public WxWorkFlowAction getMatchedWorkFlowAction(WxMessageBase messageBase) {
		for (WxWorkFlowAction wfAction : registerMap.values()) {
			if (wfAction.isActionMatch(messageBase)) {
				return wfAction;
			}
		}
		
		return null;
	}
}
