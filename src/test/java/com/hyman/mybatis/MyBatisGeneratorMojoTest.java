package com.hyman.mybatis;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import com.hyman.maven.plugins.mybatis.MyBatisGeneratorMojo;

public class MyBatisGeneratorMojoTest {
	
//	@Test
	public void testExecution() throws MojoExecutionException, MojoFailureException{
		MyBatisGeneratorMojo out=new MyBatisGeneratorMojo();
		out.setJdbcUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull");
		out.setJdbcUserName("test");
		out.setJdbcPassword("test");
		out.setEntityPackage("com.hyman.mybatis.model.entity");
		out.setEntityOutputDirectory("src/test/java/com/hyman/mybatis/model/entity");
		out.setMapperPackage("com.hyman.mybatis.dao.mapper");
		out.setMapperOutputDirectory("src/test/java/com/hyman/mybatis/dao/mapper");
		out.setMapperXmlOutputDirectory("src/test/resources/mybatis/mapper");
		
		out.execute();
	}
	
}
