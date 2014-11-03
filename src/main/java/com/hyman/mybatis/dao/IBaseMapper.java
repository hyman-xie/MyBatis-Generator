package com.hyman.mybatis.dao;

import org.apache.ibatis.annotations.Options;

import com.hyman.mybatis.model.entity.IEntity;


public interface IBaseMapper<Entity extends IEntity> {
	
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addEntity(final Entity entity);

	public void removeEntity(final Long id);

	public void updateEntity(final Entity entity);

	public Entity getEntity(final Long id);

}
