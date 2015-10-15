package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageLink extends WxMessageBase {
	
	MsgType msgType = MsgType.link;

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
