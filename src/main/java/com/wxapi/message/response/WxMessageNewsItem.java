package com.wxapi.message.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("item")
public class WxMessageNewsItem {

	@JsonProperty("Title")
	@XStreamAlias("Title")
	private String title;
	
	@JsonProperty("Description")
	@XStreamAlias("Description")
	private String description;
	
	@JsonProperty("PicUrl")
	@XStreamAlias("PicUrl")
	private String picUrl;
	
	@JsonProperty("Url")
	@XStreamAlias("Url")
	private String url;
}
