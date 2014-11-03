package com.hyman.mybatis.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.hyman.mybatis.model.Column;
import com.hyman.mybatis.model.Table;


public class ClassGenerator {
	
	public ClassGenerator() {
		
	}
	
	public void generateClass(String entityDirectory, String entityPackage,Table table) throws IOException{
		File dir=new File(entityDirectory);
		if(!dir.exists()){			
			dir.mkdirs();
		}
		File file=new File(entityDirectory+File.separator+table.getName()+".java");
		FileWriter fileWriter=new FileWriter(file);
		StringBuffer out=new StringBuffer();
		if(entityPackage!=null){			
			out.append("package "+entityPackage+";\n");
			out.append("\n");
		}
		out.append("import java.util.Date;\n");
		out.append("import com.hyman.mybatis.model.entity.impl.ABaseEntity;\n");
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
}
