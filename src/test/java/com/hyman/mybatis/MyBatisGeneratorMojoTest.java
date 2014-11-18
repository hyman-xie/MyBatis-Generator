package com.hyman.mybatis;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.hyman.maven.plugins.mybatis.MyBatisGeneratorMojo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/config.xml")
public class MyBatisGeneratorMojoTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testExecution() throws MojoExecutionException, MojoFailureException, SQLException, IOException{
		MyBatisGeneratorMojo out=new MyBatisGeneratorMojo();
		out.setTablePrefix("test_".toUpperCase());
		out.setConnection(dataSource.getConnection());
		out.setEntityPackage("com.hyman.mybatis.model.entity");
		out.setEntityOutputDirectory("src/test/java/com/hyman/mybatis/model/entity");
		out.setMapperPackage("com.hyman.mybatis.dao.mapper");
		out.setMapperOutputDirectory("src/test/java/com/hyman/mybatis/dao/mapper");
		out.setMapperXmlOutputDirectory("src/test/resources/mybatis/mapper");
		out.execute();
		
		String entity=FileUtils.readFileToString(new File("src/test/java/com/hyman/mybatis/model/entity/impl/Product.java"));
		String mapper=FileUtils.readFileToString(new File("src/test/java/com/hyman/mybatis/dao/mapper/ProductMapper.java"));
		String mapperXml=FileUtils.readFileToString(new File("src/test/resources/mybatis/mapper/ProductMapper.xml"));
		
		String entityExpected=FileUtils.readFileToString(new File("src/test/resources/data/product/entity_expected"));
		String mapperExpected=FileUtils.readFileToString(new File("src/test/resources/data/product/mapper_expected"));
		String mapperXmlExpected=FileUtils.readFileToString(new File("src/test/resources/data/product/mapperxml_expected"));
		
		Assert.isTrue(entity.compareTo(entityExpected)==0);
		Assert.isTrue(mapper.compareTo(mapperExpected)==0);
		Assert.isTrue(mapperXml.compareTo(mapperXmlExpected)==0);
	}
	
	@After
	public void cleanUp() throws IOException{
		FileUtils.cleanDirectory(new File("src/test/resources/mybatis/mapper"));
		FileUtils.cleanDirectory(new File("src/test/java/com/hyman/mybatis/dao/mapper"));
		FileUtils.cleanDirectory(new File("src/test/java/com/hyman/mybatis/model/entity"));
	}
}
