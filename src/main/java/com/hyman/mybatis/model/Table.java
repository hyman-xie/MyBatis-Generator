package com.hyman.mybatis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {

	private static final long serialVersionUID = 3060874701206142000L;
	
	private String dbName;
	private String name;
	private List<Column> columns=new ArrayList<Column>();
	
	public Table() {
		
	}
	public Table(String name, List<Column> columns) {
		super();
		this.name = name;
		this.columns = columns;
	}
	public Table(String dbName, String name, List<Column> columns) {
		super();
		this.dbName = dbName;
		this.name = name;
		this.columns = columns;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	@Override
	public String toString() {
		return "Table [dbName=" + dbName + ", name=" + name + ", column=" + columns + "]";
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
