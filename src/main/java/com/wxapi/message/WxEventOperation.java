package com.wxapi.message;

public enum WxEventOperation {
	//Query operation
	QUERY_CHINESE,
	QUERY_MATH,
	QUERY_ENGLISH,
	QUERY_MESSAGE,
	QUERY_ALL,
	
	//Release operation
	RELEASE_CHINESE,
	RELEASE_MATH,
	RELEASE_ENGLISH,
	RELEASE_MESSAGE
	;
	
	public boolean needStartWorkFlow(){
		switch(this) {
		case RELEASE_CHINESE:
		case RELEASE_MATH:
		case RELEASE_ENGLISH:
		case RELEASE_MESSAGE:
			return true;
		default:
			return false;
		}
	}
	
	
}
