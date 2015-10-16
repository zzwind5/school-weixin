package com.wxapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.data.model.BaseWxDoInVhm;
import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxMsgType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "msg_type", discriminatorType = DiscriminatorType.STRING)
@Table(name="wx_school_message")
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class WxSchoolMessage extends BaseWxDoInVhm {
	
	private static final long serialVersionUID = 1L;

	@Column(name="from_user_name", length=DEFAULT_STRING_LENGTH)
	private String fromUserName;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name="msg_type", length=DEFAULT_SHORT_STRING_LENGTH)
	@Transient
	protected WxMsgType wxMsgType;
	
	@Column(name="msg_id")
	private Long msgId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="wx_menu_key", length=DEFAULT_STRING_LENGTH)
	private WxEventOperation wxMenuKey;
}
