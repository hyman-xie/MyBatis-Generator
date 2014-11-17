package com.hyman.mybatis.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hyman.mybatis.model.Table;


public class MapperXmlGenerator {

	public MapperXmlGenerator() {
		
	}
	
	public void generateTemplate(String mapperXmlDirecotry, String mapperXmlContent, Table table) throws IOException{
		FileUtils.writeStringToFile(new File(mapperXmlDirecotry+File.separator+table.getName()+"Mapper.xml"), mapperXmlContent, "UTF-8");
	}
	
}
