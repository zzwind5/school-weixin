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
@DiscriminatorValue(WxMsgType.VIDEO)
public class WxSchoolMessageVideo extends WxSchoolMessage {
	
	private static final long serialVersionUID = 1L;

	@Transient
	protected WxMsgType wxMsgType = WxMsgType.video;

	@Column(name="media_id")
	private Long mediaId;
	
	@Column(name="thumb_media_id")
	private Long thumbMediaId;
}
