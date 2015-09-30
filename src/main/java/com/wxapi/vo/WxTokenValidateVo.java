package com.wxapi.vo;

import lombok.Getter;
import lombok.Setter;

import com.core.vo.BaseVo;

@Getter
@Setter
public class WxTokenValidateVo extends BaseVo {

	private String signature;	// 微信加密签名
	private String timestamp;	// 时间戳
	private String nonce;		// 随机数
	private String echostr;		// 随机字符串
}
