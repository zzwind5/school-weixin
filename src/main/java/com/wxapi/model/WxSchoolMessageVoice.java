package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.wxapi.message.WxMsgType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(WxMsgType.VOICE)
public class WxSchoolMessageVoice extends WxSchoolMessage {
	
	private static final long serialVersionUID = 1L;

	@Column(name="media_id")
	private Long mediaId;
	
	@Column(name="format", length=DEFAULT_SHORT_STRING_LENGTH)
	private String format;
}
