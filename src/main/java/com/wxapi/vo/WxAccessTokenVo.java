package com.wxapi.vo;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class WxAccessTokenVo {

	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private Long expiresIn; 
}
