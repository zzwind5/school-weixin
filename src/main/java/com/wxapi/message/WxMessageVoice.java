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
public class WxMessageVoice extends WxMessageBase {
	
	WxMsgType msgType = WxMsgType.voice;

	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private Long mediaId;
	
	@JsonProperty("Format")
	@XStreamAlias("Format")
	private String format;
}
