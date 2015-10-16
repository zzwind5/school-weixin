package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@XStreamAlias("xml")
public class WxMessageVideo extends WxMessageBase {
	
	WxMsgType msgType = WxMsgType.video;

	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private String mediaId;
	
	@JsonProperty("ThumbMediaId")
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;
}
