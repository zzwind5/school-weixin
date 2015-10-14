package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "MsgType")
@JsonSubTypes({
      @JsonSubTypes.Type(name = "text",				value = WxMessageText.class)
    , @JsonSubTypes.Type(name = "image",				value = WxMessageImage.class)
    , @JsonSubTypes.Type(name = "voice",				value = WxMessageVoice.class)
    , @JsonSubTypes.Type(name = "video",				value = WxMessageVideo.class)
    , @JsonSubTypes.Type(name = "shortvideo",		value = WxMessageVideoShort.class)
	, @JsonSubTypes.Type(name = "location",			value = WxMessageLocation.class)
	, @JsonSubTypes.Type(name = "link",				value = WxMessageLink.class)
})
@Data
@EqualsAndHashCode
public abstract class WxMessageBase {

	@JsonProperty("ToUserName")
	@XStreamAlias("ToUserName")
	private String toUserName;
	
	@JsonProperty("FromUserName")
	@XStreamAlias("FromUserName")
	private String fromUserName;
	
	@JsonProperty("CreateTime")
	@XStreamAlias("CreateTime")
	private Long createTime;
	
	@JsonProperty("msgType")
	@XStreamAlias("msgType")
	private MsgType msgType;
	
	@JsonProperty("MsgId")
	@XStreamAlias("MsgId")
	private Long msgId;
}
