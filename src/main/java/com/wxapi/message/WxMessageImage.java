package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageImage extends WxMessageBase {

	@JsonProperty("PicUrl")
	@XStreamAlias("PicUrl")
	private String picUrl;
	
	@JsonProperty("MediaId")
	@XStreamAlias("MediaId")
	private Long mediaId;
}
