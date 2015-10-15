package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@XStreamAlias("xml")
public class WxMessageLink extends WxMessageBase {
	
	WxMsgType msgType = WxMsgType.link;

	@JsonProperty("Title")
	@XStreamAlias("Title")
	private String title;
	
	@JsonProperty("Description")
	@XStreamAlias("Description")
	private String description;
	
	@JsonProperty("Url")
	@XStreamAlias("Url")
	private String url;
}
