package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "MsgType")
@JsonSubTypes({
      @Type(name = "text",				value = WxMessageText.class)
    , @Type(name = "image",				value = WxMessageImage.class)
    , @Type(name = "voice",				value = WxMessageVoice.class)
    , @Type(name = "video",				value = WxMessageVideo.class)
    , @Type(name = "shortvideo",		value = WxMessageVideoShort.class)
	, @Type(name = "location",			value = WxMessageLocation.class)
	, @Type(name = "link",				value = WxMessageLink.class)
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
	protected MsgType msgType;
	
	@JsonProperty("MsgId")
	@XStreamAlias("MsgId")
	private Long msgId;
}
