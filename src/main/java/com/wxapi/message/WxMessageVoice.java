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
public class WxMessageVoice extends WxMessageBase {
	
	WxMsgType msgType = WxMsgType.voice;

	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private String mediaId;
	
	@JsonProperty("Format")
	@XStreamAlias("Format")
	private String format;
	
	@JsonProperty("Recognition")
	@XStreamAlias("Recognition")
	private String recognition;
}
