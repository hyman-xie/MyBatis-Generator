package com.hyman.mybatis;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import com.hyman.mybatis.service.TemplateService;

public class TemplateServiceTest extends ABaseTest {

	@Test
	public void testTemplate() throws Exception {
		TemplateService templateService = new TemplateService();
		String out = templateService.freemarkerDo("/mybatis/template/MapperTemplate.xml", "com.hyman", "com.hyman", generateTable());
		String expectedOut=FileUtils.readFileToString(new File("src/test/resources/data/hyman/mapperxml_expected"));
		Assert.isTrue(out.compareTo(expectedOut)==0);
	}

}
