package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMessageVideo extends WxMessageBase {

	@JsonProperty("MediaId")
	private Long mediaId;
	
	@JsonProperty("ThumbMediaId")
	private Long thumbMediaId;
}
