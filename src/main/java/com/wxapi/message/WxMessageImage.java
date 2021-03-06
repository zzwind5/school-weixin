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
public class WxMessageImage extends WxMessageBase {
	
	WxMsgType msgType = WxMsgType.image;
	
	@JsonProperty("PicUrl")
	@XStreamAlias("PicUrl")
	private String picUrl;
	
	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private String mediaId;
	
}
