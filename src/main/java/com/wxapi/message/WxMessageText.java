package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.codehaus.jackson.annotate.JsonProperty;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageText extends WxMessageBase {

	@JsonProperty("Content")
	@XStreamAlias("Content")
	private String content;
}
