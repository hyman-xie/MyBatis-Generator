package com.hyman.mybatis.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.hyman.mybatis.model.Table;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplateService{
		
	private Template getTemplate(String fieldpath) throws IOException {
		String template = null;
		try {
			File file=new File(fieldpath);
			template=FileUtils.readFileToString(file);
			Configuration freemarkerCfg = new Configuration();
			StringTemplateLoader tl = new StringTemplateLoader();
			freemarkerCfg.setURLEscapingCharset("utf-8");
			freemarkerCfg.setTemplateLoader(tl);

			tl.putTemplate("a", template);
			return freemarkerCfg.getTemplate("a");
		} catch (Exception e) {
			throw new IOException("error loading template", e);
		}
	}
	
	public String freemarkerDo(String fieldPath, String entityPackage, String mapperPackage,Table table) throws Exception {
		try {
			Map<String, Object> datamodel=new HashMap<String, Object>();
			datamodel.put("ENTITY_PACKAGE", entityPackage);
			datamodel.put("MAPPER_PACKAGE", mapperPackage);
			datamodel.put("table", table);
			return freemarkerDo(datamodel, fieldPath);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String freemarkerDo(Map<String, Object> datamodel, String fieldPath) throws Exception {
		try {
			Template t = getTemplate(fieldPath);
			if(t==null) {
				return null;
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(baos);
			t.process(datamodel, osw);
			osw.flush();
			osw.close();
			return new String(baos.toByteArray(), "utf-8");
		} catch (Exception e) {
			throw e;
		}
	}
}
