package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.data.model.BaseWxDo;

@Entity
@Table(name="wx_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxUser extends BaseWxDo<Long> {
	
	private static final long serialVersionUID = 1L;

	@Column(name="user_name", length=DEFAULT_STRING_LENGTH)
	private String userName;
	
	@Column(name="owner_id")
	@NotNull
	private Long ownerId;
}
