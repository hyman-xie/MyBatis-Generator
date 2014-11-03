package com.hyman.mybatis.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hyman.mybatis.model.Column;
import com.hyman.mybatis.model.Table;

public class TableService {
	
	private String delimiter="_";
	private String tablePrefix="bc";
	private String url;
	private String username;
	private String password;
	
	public TableService(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

	private String formatName(String prefix,String delimiter,String name){
		String out=name;
		if(prefix!=null){			
			out=name.replace(prefix, "");
		}
		while(out.lastIndexOf(delimiter)!=-1){
			int index=out.lastIndexOf(delimiter);
			String i=out.substring(index+1, index+2);
			i=i.toUpperCase();
			out=out.replace(delimiter+i.toLowerCase(), i);
		}
		return out;
	}
	
	private String getJavaTypeFromJDBCType(String type){
		String out="String";
		if(type!=null){
			if(type.contains("INT")){
				return "Long";
			}else if(type.contains("VARCHAR")){
				return "String";
			}else if(type.contains("DATETIME")){
				return "Date";
			}
		}
		return out;
	}
	
	public DatabaseMetaData getDatabaseMetaData() throws SQLException{
		Connection conn=DriverManager.getConnection(url, username, password);
		return conn.getMetaData();
	}
	
	public List<Table> getTablesFromMetaData() throws SQLException, IOException{
		DatabaseMetaData databaseMetaData=getDatabaseMetaData();
		List<Table> out=new ArrayList<Table>();
		ResultSet resultSet=databaseMetaData.getTables(null, null, tablePrefix+"%", null);
		while(resultSet.next()){
			Table table=new Table();
			String originalTableName=resultSet.getString("TABLE_NAME");
			String tableName=formatName(tablePrefix, delimiter, originalTableName);
			ResultSet columnResultSet=databaseMetaData.getColumns(null, null, originalTableName, null);
			List<Column> columns=new ArrayList<Column>();
			while(columnResultSet.next()){
				String columnType=columnResultSet.getString("TYPE_NAME");
				String originalColumnName=columnResultSet.getString("COLUMN_NAME");
				int columnSize = columnResultSet.getInt("COLUMN_SIZE");
				String columnName=formatName(null, delimiter, originalColumnName);
				Column column=new Column();
				column.setName(columnName);
				column.setDbName(originalColumnName);
				column.setDbType(columnType);
				column.setType(getJavaTypeFromJDBCType(columnType));
				column.setSize(columnSize);
				columns.add(column);
			}
			table.setName(tableName);
			table.setDbName(originalTableName);
			table.setColumns(columns);
			
			out.add(table);
		}
		return out;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
