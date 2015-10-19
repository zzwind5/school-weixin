package com.wxapi.message;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@XStreamAlias("xml")
public class WxMessageNews extends WxMessageBase {

	WxMsgType msgType = WxMsgType.news;
	
	@JsonProperty("Content")
	@XStreamAlias("Content")
	private String content;
	
	@JsonProperty("ArticleCount")
	@XStreamAlias("ArticleCount")
	private int articleCount;
	
	@JsonProperty("FuncFlag")
	@XStreamAlias("FuncFlag")
	private int funcFlag;
	
	@JsonProperty("Articles")
	@XStreamAlias("Articles")
	private List<WxMessageNewsItem> articles = new ArrayList<>();
}
