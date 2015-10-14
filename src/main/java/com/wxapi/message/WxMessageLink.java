package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMessageLink extends WxMessageBase {

	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("Url")
	private String url;
}
