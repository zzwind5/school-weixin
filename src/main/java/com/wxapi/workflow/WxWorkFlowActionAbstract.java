package com.wxapi.workflow;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class WxWorkFlowActionAbstract implements WxWorkFlowAction {

	@Autowired
	private WxWorkflowManager workFlowManager;
	
	@PostConstruct
	public void register(){
		workFlowManager.register(this);
	}
}
