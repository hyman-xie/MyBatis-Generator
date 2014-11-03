package com.hyman.mybatis;

import org.junit.Test;

import com.hyman.mybatis.generator.EntityGenerator;
import com.hyman.mybatis.generator.MapperGenerator;
import com.hyman.mybatis.generator.MyBatisGenerator;
import com.hyman.mybatis.generator.TemplateGenerator;
import com.hyman.mybatis.service.TableService;
import com.hyman.mybatis.service.TemplateService;

public class MyBatisGeneratorTest {
	
	@Test
	public void testGenerate() throws Exception{
		String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull";
		String user="test";
		String password="test";

		TableService tableService=new TableService(url, user, password);
		TemplateService templateService=new TemplateService();
		EntityGenerator classGenerator=new EntityGenerator();
		TemplateGenerator templateGenerator=new TemplateGenerator();
		MapperGenerator mapperGenerator=new MapperGenerator();
		MyBatisGenerator myBatisGenerator=new MyBatisGenerator(tableService, templateService, classGenerator, templateGenerator,mapperGenerator);
		myBatisGenerator.generate("com.hyman.mybatis.model", "src/test/java/com/hyman/mybatis/model", "com.hyman.mybatis.dao.mapper", "src/main/resources/mybatis/mapper/MapperTemplate.xml", "src/test/resources/mybatis/mapper", "src/test/java/com/hyman/mybatis/dao/mapper");
	}
	
}
