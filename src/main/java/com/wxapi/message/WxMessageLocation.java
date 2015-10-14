package com.wxapi.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMessageLocation extends WxMessageBase {

	@JsonProperty("Location_X")
	private double location_X;
	
	@JsonProperty("Location_Y")
	private double location_Y;
	
	@JsonProperty("Scale")
	private Integer scale;
	
	@JsonProperty("Label")
	private String label;
}
