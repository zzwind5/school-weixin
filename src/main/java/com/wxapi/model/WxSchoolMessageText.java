package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.wxapi.message.WxMsgType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(WxMsgType.TEXT)
public class WxSchoolMessageText extends WxSchoolMessage {

	private static final long serialVersionUID = 1L;
	
	@Column(name="content", length=DEFAULT_LARGE_STRING_LENGTH)
	private String content;

}
