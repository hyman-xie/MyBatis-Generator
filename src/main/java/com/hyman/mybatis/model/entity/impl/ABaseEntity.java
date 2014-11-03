package com.hyman.mybatis.model.entity.impl;

import com.hyman.mybatis.model.entity.IEntity;

public abstract class ABaseEntity implements IEntity {

	private static final long serialVersionUID = -2975424897106620060L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
