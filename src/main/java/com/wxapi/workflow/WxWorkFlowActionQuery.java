package com.wxapi.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxapi.cache.WxHomeWorkCache;
import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxMessageBase;

@Component
public class WxWorkFlowActionQuery extends WxWorkFlowActionAbstract {
	
	@Autowired
	private WxHomeWorkCache homeWorkCache;

	@Override
	public boolean isActionMatch(WxMessageBase message) {
		WxEventOperation queryOpt = this.getOperationEnum(message);
		if (queryOpt == null) {
			return false;
		}
		switch(queryOpt) {
			case QUERY_CHINESE:
			case QUERY_MATH:
			case QUERY_ENGLISH:
			case QUERY_MESSAGE:
			case QUERY_ALL:
				return true;
			default:
				return false;
		}
	}

	@Override
	public WxMessageBase process(WxMessageBase message) {
		WxEventOperation queryOpt = this.getOperationEnum(message);
		return homeWorkCache.queryHomeWork(queryOpt, message);
	}

}
