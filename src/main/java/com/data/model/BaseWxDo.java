package com.data.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EqualsAndHashCode(callSuper = false, of = {})
public abstract class BaseWxDo<PK extends Serializable> extends WxTimeStamp 
	implements WxDo, Cloneable, Serializable {

	private static final long serialVersionUID = -9892832637945768L;

	@Id
	@Getter @Setter
	@GeneratedValue
	protected PK id;
}
