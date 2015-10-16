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
@DiscriminatorValue(WxMsgType.IMAGE)
public class WxSchoolMessageImage extends WxSchoolMessage {

	private static final long serialVersionUID = 1L;
	
	@Transient
	protected WxMsgType wxMsgType = WxMsgType.image;

	@Column(name="pic_url", length=DEFAULT_URL_LENGTH)
	private String picUrl;
	
	@Column(name="media_id", length=STRING_128)
	private String mediaId;
}
