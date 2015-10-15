package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageImage extends WxMessageBase {
	
	MsgType msgType = MsgType.image;
	
	@JsonProperty("PicUrl")
	@XStreamAlias("PicUrl")
	private String picUrl;
	
	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private Long mediaId;
	
}
