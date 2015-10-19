package com.wxapi.cache;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxMessageBase;
import com.wxapi.model.WxSchoolMessage;

@Component
public class WxHomeWorkCache {

	private HashMap<WxEventOperation, WxSchoolMessage> homeWorkCache = new HashMap<>();
	
	private WxMessageBase querySource;
	
	public void cache(WxSchoolMessage schoolMsg) {
		homeWorkCache.put(schoolMsg.getWxMenuKey(), schoolMsg);
	}
	
	private void initQuerySource() {
		for (WxSchoolMessage schMsg : homeWorkCache.values()) {
			
		}
	}
}