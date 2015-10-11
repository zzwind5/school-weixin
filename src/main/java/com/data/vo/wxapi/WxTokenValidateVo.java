package com.data.vo.wxapi;

import lombok.Getter;
import lombok.Setter;

import com.data.vo.BaseVo;

@Getter
@Setter
public class WxTokenValidateVo extends BaseVo {

	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
}