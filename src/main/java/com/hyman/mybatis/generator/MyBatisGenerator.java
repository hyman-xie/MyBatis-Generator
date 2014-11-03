package com.hyman.mybatis.generator;

import java.util.List;

import com.hyman.mybatis.model.Table;
import com.hyman.mybatis.service.TableService;
import com.hyman.mybatis.service.TemplateService;

public class MyBatisGenerator {
	
	private TableService tableService;
	private TemplateService templateService;
	private EntityGenerator classGenerator;
	private TemplateGenerator templateGenerator;
	private MapperGenerator mapperGenerator;
	
	public MyBatisGenerator(TableService tableService, TemplateService templateService, EntityGenerator classGenerator, TemplateGenerator templateGenerator, MapperGenerator mapperGenerator) {
		this.tableService = tableService;
		this.templateService = templateService;
		this.classGenerator = classGenerator;
		this.templateGenerator = templateGenerator;
		this.mapperGenerator=mapperGenerator;
	}

	public void generate(String entityPackage, String entityOutputDirectory,String mapperPackage, String templateFilePath, String templateOutputDirectory, String mapperOutputDirectory) throws Exception{
		List<Table> tables=tableService.getTablesFromMetaData();
		for(Table table : tables){
			String template=templateService.freemarkerDo(templateFilePath, entityPackage, mapperPackage,table);
			classGenerator.generateClass(entityOutputDirectory, entityPackage, table);
			templateGenerator.generateTemplate(templateOutputDirectory, template, table);
			mapperGenerator.generateMapper(entityPackage, mapperPackage, mapperOutputDirectory, table);
		}
	}
}
