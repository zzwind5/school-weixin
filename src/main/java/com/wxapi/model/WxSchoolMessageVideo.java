package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.wxapi.message.WxMsgType;

@Data @EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(WxMsgType.VIDEO)
public class WxSchoolMessageVideo extends WxSchoolMessage {
	
	private static final long serialVersionUID = 1L;

	@Getter
	protected WxMsgType wxMsgType = WxMsgType.video;

	@Column(name="media_id")
	private Long mediaId;
	
	@Column(name="thumb_media_id")
	private Long thumbMediaId;
}
