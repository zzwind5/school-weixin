package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@XStreamAlias("xml")
public class WxMessageEvent extends WxMessageBase {
	
	protected WxMsgType msgType = WxMsgType.event;
	
	@JsonProperty("Event")
	@XStreamAlias("Event")
	protected WxEventType event;

	@JsonProperty("EventKey")
	@XStreamAlias("EventKey")
	private WxEventOperation eventKey;
	
	@JsonIgnore
	@XStreamOmitField
	protected Long msgId;
}
