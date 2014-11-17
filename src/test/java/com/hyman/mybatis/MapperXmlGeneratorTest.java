package com.hyman.mybatis;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.util.Assert;

import com.hyman.mybatis.generator.MapperXmlGenerator;

public class MapperXmlGeneratorTest extends ABaseTest {
	
	@Test
	public void testGenerateTemplate() throws IOException{
		MapperXmlGenerator mapperXmlGenerator=new MapperXmlGenerator();
		String outExpected=FileUtils.readFileToString(new File("src/test/resources/data/hyman/mapperxml_expected"));
		mapperXmlGenerator.generateTemplate("src/test/resources/mybatis/mapper", outExpected, generateTable());
		String out=FileUtils.readFileToString(new File("src/test/resources/mybatis/mapper/HymanMapper.xml"));
		Assert.isTrue(out.compareTo(outExpected)==0);
	}
	
	@After
	public void cleanUp() throws IOException{
		FileUtils.cleanDirectory(new File("src/test/resources/mybatis/mapper"));
	}
}
