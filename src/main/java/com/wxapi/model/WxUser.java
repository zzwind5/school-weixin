package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.data.model.BaseWxDoInVhm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="wx_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxUser extends BaseWxDoInVhm {
	
	private static final long serialVersionUID = 1L;

	@Column(name="user_name", length=DEFAULT_STRING_LENGTH)
	private String userName;
}
