package com.core.task.runner.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.job.Task;
import com.core.job.TaskResult;
import com.core.task.runner.AbstractorTaskRunner;
import com.fileServer.qiniu.FileServerQiNiu;
import com.wxapi.cache.WxHomeWorkCache;
import com.wxapi.cache.WxOwnerCache;
import com.wxapi.model.WxSchoolMessageImage;
import com.wxapi.model.WxSchoolMessageVoice;
import com.wxapi.process.WxApiHelper;
import com.wxapi.repositories.WxSchoolMessageRepository;

@Slf4j
@Component
public class WxMaterialStoreRunner extends AbstractorTaskRunner {
	
	@Autowired private FileServerQiNiu qiNiuServer;
	@Autowired private WxOwnerCache ownerCache;
	@Autowired private WxApiHelper wxApiHelper;
	@Autowired private WxSchoolMessageRepository msgRepo;
	@Autowired private WxHomeWorkCache homeWorkCache;

	@Override
	protected void doRun(Task task, TaskResult taskResult) {
		System.out.println("WxMaterialStoreRunner start");
		WxSchoolMessageImage imageMsg = task.getJobSpec().getJobParams(WxSchoolMessageImage.class);
		if (imageMsg != null) {
			String resourceUrl = storeImage(imageMsg.getPicUrl());
			imageMsg.setExternalUrl(resourceUrl);
			msgRepo.updateExternalUrlById(resourceUrl, imageMsg.getId());
			homeWorkCache.cache(imageMsg);
			return;
		}
		
		WxSchoolMessageVoice voiceMsg = task.getJobSpec().getJobParams(WxSchoolMessageVoice.class);
		if (voiceMsg != null) {
			String resourceUrl = storeVoice(voiceMsg.getMediaId(), voiceMsg.getOwnerId());
			voiceMsg.setExternalUrl(resourceUrl);
			msgRepo.updateExternalUrlById(resourceUrl, voiceMsg.getId());
			homeWorkCache.cache(voiceMsg);
			return;
		}
		
		log.info("Illegal job param {}", task.getJobSpec());
	}
	
	private String storeImage(String targetUrl) {
		return qiNiuServer.fetch(targetUrl);
	}
	
	private String storeVoice(String mediaId, Long ownerId) {
		String mediaUrl = wxApiHelper.getMediaFetchUrl(mediaId, ownerId);
		String amrUrl = qiNiuServer.fetch(mediaUrl);
		String key = amrUrl.substring(amrUrl.lastIndexOf("/") + 1);
		return qiNiuServer.amr2map3(key);
	}
}
