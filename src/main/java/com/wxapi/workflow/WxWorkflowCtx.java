package com.wxapi.workflow;

import java.util.ArrayList;
import java.util.List;

import com.wxapi.message.WxMessageBase;

import lombok.Data;

@Data
public class WxWorkflowCtx {
	
	private static final String KEY_FORMAT = "WX_WF_%s_%s";
	
	private String workFlowCtxKey;
	
	private String workFlowActionKey;
	
	private int steps;
	
	private List<WxMessageBase> messageCtx = new ArrayList<>();
	
	public WxWorkflowCtx(WxMessageBase message) {
		this.workFlowCtxKey = generateKey(message);
	}
	
	public void cache(WxMessageBase message){
		messageCtx.add(message);
	}
	
	public int getNextStepIndex() {
		return messageCtx.size();
	}
	
	public int getLatestStepIndex() {
		return messageCtx.size() - 1;
	}
	
	public WxMessageBase getLastOne(){
		if (messageCtx.isEmpty()) {
			return null;
		} else {
			return messageCtx.get(messageCtx.size() - 1);
		}
	}
	
	public WxMessageBase getWxMessage(int index){
		return messageCtx.get(index);
	}
	
	public boolean isFinished(){
		return steps == messageCtx.size();
	}
	
	public static String generateKey(String wxUserName, Long ownerId) {
		return String.format(KEY_FORMAT, wxUserName, ownerId);
	}
	
	public static String generateKey(WxMessageBase message) {
		return generateKey(message.getFromUserName(), message.getOwnerId());
	}
}
