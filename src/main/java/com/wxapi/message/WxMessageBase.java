package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "MsgType")
@JsonSubTypes({
      @Type(name = WxMsgType.TEXT,				value = WxMessageText.class)
    , @Type(name = WxMsgType.IMAGE,				value = WxMessageImage.class)
    , @Type(name = WxMsgType.VOICE,				value = WxMessageVoice.class)
    , @Type(name = WxMsgType.VIDEO,				value = WxMessageVideo.class)
    , @Type(name = WxMsgType.SHORT_VIDEO,		value = WxMessageVideoShort.class)
	, @Type(name = WxMsgType.LOCATION,			value = WxMessageLocation.class)
	, @Type(name = WxMsgType.LINK,				value = WxMessageLink.class)
	, @Type(name = WxMsgType.EVENT,				value = WxMessageEvent.class)
})
@Data
@EqualsAndHashCode
public abstract class WxMessageBase implements Cloneable {

	@JsonProperty("ToUserName")
	@XStreamAlias("ToUserName")
	private String toUserName;
	
	@JsonProperty("FromUserName")
	@XStreamAlias("FromUserName")
	private String fromUserName;
	
	@JsonProperty("CreateTime")
	@XStreamAlias("CreateTime")
	private Long createTime;
	
	@JsonProperty("MsgType")
	@XStreamAlias("MsgType")
	protected WxMsgType msgType;
	
	@JsonProperty("MsgId")
	@XStreamAlias("MsgId")
	protected Long msgId;
	
	@JsonIgnore
	@XStreamOmitField
	private Long ownerId;
}
