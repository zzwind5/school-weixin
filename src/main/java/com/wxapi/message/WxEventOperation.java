package com.wxapi.message;

import lombok.Getter;

public enum WxEventOperation {
	//Query operation
	QUERY_ALL("10"),
	QUERY_CHINESE("11"),
	QUERY_MATH("12"),
	QUERY_ENGLISH("13"),
	QUERY_MESSAGE("14"),
	
	
	//Release operation
	RELEASE_CHINESE("21"),
	RELEASE_MATH("22"),
	RELEASE_ENGLISH("23"),
	RELEASE_MESSAGE("24");
	
	@Getter
	private final String key;
	
	private WxEventOperation(String key) {
		this.key = key;
	}
	
	public static WxEventOperation getEnumByKey(String key) {
		for (WxEventOperation optEnum : WxEventOperation.values()) {
			if (optEnum.getKey().equals(key)) {
				return optEnum;
			}
		}
		return null;
	}
}
