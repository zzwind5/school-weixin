package com.wxapi.vo;

import lombok.Getter;
import lombok.Setter;

import com.data.vo.WxRequestBaseVo;

@Getter
@Setter
public class WxTokenValidateVo extends WxRequestBaseVo {

	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
}