package com.wxapi.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wxapi.message.WxMessageBase;
import com.wxapi.repositories.WxSchoolMessageRepository;

@Component
@Scope("prototype")
public class WxWorkFlowReleaseMsg extends WxWorkflowCtx<WxMessageBase> {
	
	protected WxWorkflowStep[] steps = {WxWorkflowStep.MENU_CLICK, WxWorkflowStep.SEND_MSG};
	
	@Autowired
	private WxSchoolMessageRepository schMsgRep;
	
//	@Override
//	protected void close(JpaRepository rep){
//		WxMessageBase 
//	}
}
