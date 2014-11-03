package com.hyman.mybatis.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.hyman.mybatis.model.Table;

public class MapperGenerator {
	
	public MapperGenerator(){
		
	}
	
	public void generateMapper(String entityPackage, String mapperPackage, String mapperOutputDirectory, Table table) throws IOException{
		File dir=new File(mapperOutputDirectory);
		if(!dir.exists()){			
			dir.mkdirs();
		}
		File file=new File(mapperOutputDirectory+File.separator+table.getName()+"Mapper.java");
		FileWriter fileWriter=new FileWriter(file);
		StringBuffer out=new StringBuffer();
		if(mapperPackage!=null){			
			out.append("package "+mapperPackage+";\n");
			out.append("\n");
		}
		out.append("import com.hyman.mybatis.dao.IBaseMapper;\n");
		out.append("import "+entityPackage+"."+table.getName()+";\n");
		out.append("\n");
		out.append("public interface "+table.getName()+"Mapper extends IBaseMapper<"+table.getName()+">{\n");
		out.append("\n");
		out.append("\n}");
		
		fileWriter.write(out.toString());
		fileWriter.close();
	}
	
}
