package com.hyman.mybatis;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.util.Assert;

import com.hyman.mybatis.generator.MapperGenerator;

public class MapperGeneratorTest extends ABaseTest {
	
	@Test
	public void testGenerateMapper() throws IOException{
		MapperGenerator mapperGenerator=new MapperGenerator();
		mapperGenerator.generateMapper("com.hyman.mybatis.model.entity", "com.hyman.mybatis.dao.mapper", "src/test/java/com/hyman/mybatis/dao/mapper", generateTable());
		String out=FileUtils.readFileToString(new File("src/test/java/com/hyman/mybatis/dao/mapper/HymanMapper.java"));
		String outExpected=FileUtils.readFileToString(new File("src/test/resources/data/hyman/mapper_expected"));
		Assert.isTrue(out.compareTo(outExpected)==0);
	}
	
	@After
	public void cleanUp() throws IOException{
		FileUtils.cleanDirectory(new File("src/test/java/com/hyman/mybatis/dao/mapper"));
	}
}
