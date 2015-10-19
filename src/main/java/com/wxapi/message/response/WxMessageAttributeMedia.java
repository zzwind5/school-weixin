package com.wxapi.message.response;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
public class WxMessageAttributeMedia {

	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private String mediaId;
}
