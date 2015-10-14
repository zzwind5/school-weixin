package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageVideoShort extends WxMessageBase {

	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private Long mediaId;
	
	@JsonProperty("ThumbMediaId")
	@XStreamAlias("ThumbMediaId")
	private Long thumbMediaId;
}
