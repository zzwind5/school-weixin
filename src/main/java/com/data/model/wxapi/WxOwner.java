package com.data.model.wxapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.data.model.BaseWxDo;

@Entity
@Table(name="wx_owner")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOwner extends BaseWxDo<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name="owner_name", length=DEFAULT_STRING_LENGTH)
	private String ownerName;
	
	@Column(name="description", length=DEFAULT_DESCRIPTION_LENGTH)
	private String description;
	
	@Column(name="app_id", length=DEFAULT_STRING_LENGTH)
	private String appId;
	
	@Column(name="app_secret", length=STRING_256)
	private String appSecret;
	
	@Column(name="token", length=DEFAULT_STRING_LENGTH)
	private String token;
}
