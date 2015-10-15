package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageVoice extends WxMessageBase {
	
	MsgType msgType = MsgType.voice;

	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private Long mediaId;
	
	@JsonProperty("Format")
	@XStreamAlias("Format")
	private Long format;
}
