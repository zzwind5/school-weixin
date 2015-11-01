package com.core.task.runner.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.job.Task;
import com.core.job.TaskResult;
import com.core.task.runner.AbstractorTaskRunner;
import com.wxapi.cache.WxOwnerCache;
import com.wxapi.model.WxOwner;
import com.wxapi.process.WxApiHelper;
import com.wxapi.vo.WxAccessTokenVo;

@Slf4j
@Component
public class WxAccessTokenRefreshRunner extends AbstractorTaskRunner {
	
	@Autowired private WxOwnerCache ownerCache;
	@Autowired private WxApiHelper wxApiHelper;

	@Override
	protected void doRun(Task task, TaskResult taskResult) {
		log.info("Start refresh wei xin access token.");
		System.out.println("Start refresh wei xin access token.");
		
		for (WxOwner wxOwner : ownerCache.getNameCacheMap().values()) {
			WxAccessTokenVo tokenVo = wxApiHelper.getWxAccessTokenVo(wxOwner);
			wxOwner.setWxAccessToken(tokenVo.getAccessToken());
			System.out.println(wxOwner.getWxAccessToken());
		}
		
		taskResult.setSuccessful(true);
	}

}
