package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMessageImage extends WxMessageBase {

	@JsonProperty("PicUrl")
	private String picUrl;
	
	@JsonProperty("MediaId")
	private Long mediaId;
}
