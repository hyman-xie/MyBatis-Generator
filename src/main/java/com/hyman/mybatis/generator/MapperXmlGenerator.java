package com.hyman.mybatis.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hyman.mybatis.model.Table;


public class MapperXmlGenerator {

	public MapperXmlGenerator() {
		
	}
	
	public void generateTemplate(String templateDirecotry, String template, Table table) throws IOException{
		FileUtils.writeStringToFile(new File(templateDirecotry+File.separator+table.getName()+"Mapper.xml"), template, "UTF-8");
	}
	
}
