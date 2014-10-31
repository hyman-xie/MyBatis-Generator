package com.hyman.mybatis.model;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 4338961911540745581L;
	
	private String dbName;
	private String name;
	private String type;
	private int size;
	
	public Column() {
		
	}
	public Column(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	public Column(String dbName, String name, String type, int size) {
		super();
		this.dbName = dbName;
		this.name = name;
		this.type = type;
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Column [dbName=" + dbName + ", name=" + name + ", type=" + type + ", size=" + size + "]";
	}
	public String getCapitalName() {
		if(name!=null && name.trim().length()>0){			
			return name.substring(0,1).toUpperCase()+name.substring(1, name.length());
		}
		return name;
	}
}
