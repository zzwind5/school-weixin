package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMessageVideoShort extends WxMessageBase {

	@JsonProperty("MediaId")
	private Long mediaId;
	
	@JsonProperty("ThumbMediaId")
	private Long thumbMediaId;
}
