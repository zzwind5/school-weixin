package com.wxapi.workflow;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.util.WxMessageUtil;
import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxEventType;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMessageEvent;
import com.wxapi.message.WxMessageImage;
import com.wxapi.message.WxMessageText;
import com.wxapi.message.WxMessageVideo;
import com.wxapi.message.WxMessageVoice;
import com.wxapi.model.WxSchoolMessage;
import com.wxapi.model.WxSchoolMessageImage;
import com.wxapi.model.WxSchoolMessageText;
import com.wxapi.model.WxSchoolMessageVideo;
import com.wxapi.model.WxSchoolMessageVoice;
import com.wxapi.repositories.WxSchoolMessageRepository;

@Component
public class WxWorkFlowActionRelease extends WxWorkFlowActionCachedAbstract {
	
	@Autowired
	private WxSchoolMessageRepository wxSchoolMessageRepository;

	@Override
	public int getStepCount() {
		return 2;
	}

	@Override
	public boolean isNextStepMatch(int nextStepIdx, WxMessageBase message) {
		if (nextStepIdx == 0 && message instanceof WxMessageEvent) {
			WxMessageEvent msgEvent = (WxMessageEvent)message;
			return msgEvent.getEvent() == WxEventType.CLICK && 
					(msgEvent.getEventKey() == WxEventOperation.RELEASE_CHINESE
						|| msgEvent.getEventKey() == WxEventOperation.RELEASE_MATH
						|| msgEvent.getEventKey() == WxEventOperation.RELEASE_ENGLISH
						|| msgEvent.getEventKey() == WxEventOperation.RELEASE_MESSAGE
					);
		} else if (nextStepIdx == 1) {
			switch (message.getMsgType()) {
				case image:
				case text:
				case voice:
				case video:
					return true;
				default:
					return false;
			}
		}
		
		return false;
	}

	@Override
	public void closeCachedWorkFlow(WxWorkflowCtx workFlowCtx) {
		WxSchoolMessage wxMessageEntity = null;
		
		WxMessageEvent messageFirst = (WxMessageEvent)workFlowCtx.getWxMessage(0);
		WxMessageBase messageSecond = workFlowCtx.getWxMessage(1);
		
		if (messageSecond instanceof WxMessageText) {
			WxMessageText msgText = (WxMessageText)messageSecond;
			WxSchoolMessageText textEntity = new WxSchoolMessageText();
			textEntity.setContent(msgText.getContent());
			wxMessageEntity = textEntity;
		} else if (messageSecond instanceof WxMessageImage) {
			WxMessageImage msgImage = (WxMessageImage)messageSecond;
			WxSchoolMessageImage imageEntity = new WxSchoolMessageImage();
			imageEntity.setPicUrl(msgImage.getPicUrl());
			imageEntity.setMediaId(msgImage.getMediaId());
			wxMessageEntity = imageEntity;
		} else if (messageSecond instanceof WxMessageVoice) {
			WxMessageVoice msgVoice = (WxMessageVoice)messageSecond;
			WxSchoolMessageVoice voiceEntity = new WxSchoolMessageVoice();
			voiceEntity.setMediaId(msgVoice.getMediaId());
			voiceEntity.setFormat(msgVoice.getFormat());
			voiceEntity.setRecognition(msgVoice.getRecognition());
			wxMessageEntity = voiceEntity;
		} else if (messageSecond instanceof WxMessageVideo) {
			WxMessageVideo msgVideo = (WxMessageVideo)messageSecond;
			WxSchoolMessageVideo videoEntity = new WxSchoolMessageVideo();
			videoEntity.setMediaId(msgVideo.getMediaId());
			videoEntity.setThumbMediaId(msgVideo.getThumbMediaId());
			wxMessageEntity = videoEntity;
		}
		
		wxMessageEntity.setFromUserName(messageFirst.getFromUserName());
		wxMessageEntity.setOwnerId(messageFirst.getOwnerId());
		wxMessageEntity.setWxMenuKey(messageFirst.getEventKey());
		
		wxSchoolMessageRepository.saveAndFlush(wxMessageEntity);
		workFlowCtxCache.clearWxWorkflowCtx(workFlowCtx);
	}

	@Override
	public WxMessageBase getResponseMsg(int stepIndex, String fromUserName, Long ownerId) {
		if (stepIndex >= getStepCount()) {
			return null;
		}
		
		WxWorkflowCtx workFlowCtx = workFlowCtxCache.getWxWorkflowCtx(fromUserName, ownerId);
		WxMessageBase messageBase = workFlowCtx.getLastOne();
		if (messageBase == null) {
			return null;
		}
		
		WxMessageText responseText = new WxMessageText();
		responseText.setFromUserName(messageBase.getToUserName());
		responseText.setToUserName(messageBase.getFromUserName());
		responseText.setCreateTime(System.currentTimeMillis());
		
		if (stepIndex == 0) {
			responseText.setContent(WxMessageUtil.getMessage("wxapi.workflow.release.input"));
		} else if (stepIndex == 1) {
			responseText.setContent(WxMessageUtil.getMessage("wxapi.workflow.release.success"));
		}
		
		return responseText;
	}
}
