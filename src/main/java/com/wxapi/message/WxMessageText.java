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
public class WxMessageText extends WxMessageBase {
	
	WxMsgType msgType = WxMsgType.text;

	@JsonProperty("Content")
	@XStreamAlias("Content")
	private String content;
	
}
