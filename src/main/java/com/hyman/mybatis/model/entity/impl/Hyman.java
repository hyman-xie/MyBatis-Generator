package com.hyman.mybatis.model.entity.impl;

import java.util.Date;

public class Hyman extends ABaseEntity {

	private static final long serialVersionUID = 2583750403968370783L;
	private String name;
	private Date birthDate;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
