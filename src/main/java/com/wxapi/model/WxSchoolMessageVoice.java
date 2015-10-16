package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.wxapi.message.WxMsgType;

@Data @EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(WxMsgType.VOICE)
public class WxSchoolMessageVoice extends WxSchoolMessage {
	
	private static final long serialVersionUID = 1L;
	
	@Transient
	protected WxMsgType wxMsgType = WxMsgType.voice;

	@Column(name="media_id", length=STRING_128)
	private String mediaId;
	
	@Column(name="format", length=DEFAULT_SHORT_STRING_LENGTH)
	private String format;
	
	@Column(name="recognition", length=STRING_128)
	private String recognition;
}
