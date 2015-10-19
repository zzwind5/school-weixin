package com.wxapi.message.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMsgType;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@XStreamAlias("xml")
public class WxMessageResponseImage extends WxMessageBase {

	WxMsgType msgType = WxMsgType.image;
	
	@JsonProperty("Image")
	@XStreamAlias("Image")
	private WxMessageAttributeMedia image = new WxMessageAttributeMedia();
}
