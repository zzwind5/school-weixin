package com.wxapi.message.response;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
public class WxMessageAttributeVideo {

	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private String mediaId;
	
	@JsonProperty("Title")
	@XStreamAlias("Title")
	private String title;
	
	@JsonProperty("Description")
	@XStreamAlias("Description")
	private String description;
}
