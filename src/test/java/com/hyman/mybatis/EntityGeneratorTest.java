package com.hyman.mybatis;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.util.Assert;

import com.hyman.mybatis.generator.EntityGenerator;

public class EntityGeneratorTest extends ABaseTest {
	
	@Test
	public void testGenerateClass() throws IOException{
		EntityGenerator entityGenerator=new EntityGenerator();
		entityGenerator.generateClass("src/test/java/com/hyman/mybatis/model/entity", "com.hyman.mybatis.model.entity", generateTable());
		String out=FileUtils.readFileToString(new File("src/test/java/com/hyman/mybatis/model/entity/Hyman.java"));
		String outExpected=FileUtils.readFileToString(new File("src/test/resources/data/hyman/entity_expected"));
		Assert.isTrue(out.compareTo(outExpected)==0);
	}
	
	@After
	public void cleanUp() throws IOException{
		FileUtils.cleanDirectory(new File("src/test/java/com/hyman/mybatis/model/entity"));
	}
	
}
