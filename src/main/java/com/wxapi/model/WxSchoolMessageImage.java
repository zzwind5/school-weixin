package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.wxapi.message.WxMsgType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(WxMsgType.IMAGE)
public class WxSchoolMessageImage extends WxSchoolMessage {

	private static final long serialVersionUID = 1L;

	@Column(name="pic_url", length=DEFAULT_URL_LENGTH)
	private String picUrl;
	
	@Column(name="media_id")
	private Long mediaId;
}
