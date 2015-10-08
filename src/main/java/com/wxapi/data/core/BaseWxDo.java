package com.wxapi.data.core;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class BaseWxDo<PK extends Serializable> extends WxTimeStamp {

	private static final long serialVersionUID = -9892832637945768L;

	@Id
	@Getter @Setter
	@GeneratedValue
	private PK id;
}
