package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.codehaus.jackson.annotate.JsonProperty;


@Data
@EqualsAndHashCode(callSuper = true)
public class WxMessageText extends WxMessageBase {

	@JsonProperty("Content")
	private String content;
}
