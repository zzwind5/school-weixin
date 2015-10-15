package com.data.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class BaseWxDoInVhm extends BaseWxDo<Long> {

	private static final long serialVersionUID = -3750149981785677572L;
	
	@Column(name="owner_id", updatable=false)
	@NotNull
	private Long ownerId;

}
