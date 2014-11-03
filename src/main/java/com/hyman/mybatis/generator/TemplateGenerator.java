package com.hyman.mybatis.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class TemplateGenerator {

	public TemplateGenerator() {
		
	}
	
	public void generateTemplate(String templateDirecotry, String template, String name) throws IOException{
		FileUtils.writeStringToFile(new File(templateDirecotry+File.separator+name+"Mapper.xml"), template, "UTF-8");
	}
	
}
