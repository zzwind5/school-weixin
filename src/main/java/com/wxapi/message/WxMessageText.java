package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageText extends WxMessageBase {
	
	MsgType msgType = MsgType.text;

	@JsonProperty("Content")
	@XStreamAlias("Content")
	private String content;
	
}
