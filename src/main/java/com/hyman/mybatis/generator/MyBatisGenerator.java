package com.hyman.mybatis.generator;

import java.util.List;

import com.hyman.mybatis.model.Table;
import com.hyman.mybatis.service.TableService;
import com.hyman.mybatis.service.TemplateService;

public class MyBatisGenerator {
	
	private TableService tableService;
	private TemplateService templateService;
	private ClassGenerator classGenerator;
	private TemplateGenerator templateGenerator;
	
	public MyBatisGenerator(TableService tableService, TemplateService templateService, ClassGenerator classGenerator, TemplateGenerator templateGenerator) {
		this.tableService = tableService;
		this.templateService = templateService;
		this.classGenerator = classGenerator;
		this.templateGenerator = templateGenerator;
	}

	public void generate(String entityPackage, String entityOutputDirectory,String mapperPackage, String templateFilePath, String templateOutputDirectory) throws Exception{
		List<Table> tables=tableService.getTablesFromMetaData();
		for(Table table : tables){
			String template=templateService.freemarkerDo(table, templateFilePath, entityPackage, mapperPackage);
			classGenerator.generateClass(entityOutputDirectory, entityPackage, table);
			templateGenerator.generateTemplate(templateOutputDirectory, template, table.getName());
		}
	}
}
