package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMessageVoice extends WxMessageBase {

	@JsonProperty("MediaId")
	private Long mediaId;
	
	@JsonProperty("Format")
	private Long format;
}
