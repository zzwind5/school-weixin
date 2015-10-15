package com.wxapi.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class WxMessageLocation extends WxMessageBase {
	
	MsgType msgType = MsgType.location;

	@JsonProperty("Location_X")
	@XStreamAlias("Location_X")
	private double location_X;
	
	@JsonProperty("Location_Y")
	@XStreamAlias("Location_Y")
	private double location_Y;
	
	@JsonProperty("Scale")
	@XStreamAlias("Scale")
	private Integer scale;
	
	@JsonProperty("Label")
	@XStreamAlias("Label")
	private String label;
}
