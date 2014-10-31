package com.hyman.mybatis;

import java.io.File;
import java.io.FileWriter;
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


public class Generator {
	
	private String delimiter="_";
	private String tablePrefix="bc";
	private String url;
	private String username;
	private String password;
	
	public Generator(String delimiter, String tablePrefix, String url, String username, String password) {
		super();
		this.delimiter = delimiter;
		this.tablePrefix = tablePrefix;
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
	
	public void generateClass(String directory, String packagePath,Table table) throws IOException{
		File dir=new File(directory);
		if(!dir.exists()){			
			dir.mkdirs();
		}
		File file=new File(directory+File.separator+table.getName()+".java");
		FileWriter fileWriter=new FileWriter(file);
		StringBuffer out=new StringBuffer();
		if(packagePath!=null){			
			out.append("package "+packagePath+";\n");
			out.append("\n");
		}
		out.append("import java.util.Date;\n");
		out.append("import com.hyman.mybatis.model.ABaseEntity;\n");
		out.append("\n");
		out.append("public class "+table.getName()+" extends ABaseEntity{\n");
		out.append("\n");
		for(Column column : table.getColumns()){
			out.append("\t");
			out.append("private "+column.getType()+" "+column.getName()+";\n");
		}
		
		for(Column column : table.getColumns()){
			out.append("\t");
			out.append("public "+column.getType()+" get"+column.getCapitalName()+"() {");
			out.append("\n");
			out.append("\t\t");
			out.append("return "+column.getName()+";");
			out.append("\n");
			out.append("\t}");
			out.append("\n");
			
			out.append("\t");
			out.append("public void set"+column.getCapitalName()+"("+column.getType()+" "+column.getName()+") {");
			out.append("\n");
			out.append("\t\t");
			out.append("this."+column.getName()+" = "+column.getName()+";");
			out.append("\n");
			out.append("\t}");
			out.append("\n");
		}
		
		out.append("\n}");
		
		fileWriter.write(out.toString());
		fileWriter.close();
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
