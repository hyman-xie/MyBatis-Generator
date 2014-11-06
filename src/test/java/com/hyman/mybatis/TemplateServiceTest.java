package com.hyman.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hyman.mybatis.model.Column;
import com.hyman.mybatis.model.Table;
import com.hyman.mybatis.model.entity.impl.Hyman;
import com.hyman.mybatis.service.TemplateService;

public class TemplateServiceTest {

//	@Test
	public void testTemplate() throws Exception {
		TemplateService templateService = new TemplateService();
		
		Table table = new Table();
		table.setName(Hyman.class.getSimpleName());
		table.setDbName(Hyman.class.getSimpleName());
		List<Column> columns = new ArrayList<Column>();
		Column idColumn = new Column();
		idColumn.setName("id");
		idColumn.setDbName("id");
		idColumn.setType("Long");
		idColumn.setDbType("INT");
		Column nameColumn = new Column();
		nameColumn.setName("name");
		nameColumn.setDbName("name");
		nameColumn.setType("String");
		nameColumn.setDbType("VARCHAR");
		Column birthDateColumn = new Column();
		birthDateColumn.setName("birthDate");
		birthDateColumn.setDbName("birth_date");
		birthDateColumn.setType("Date");
		birthDateColumn.setDbType("DATETIME");
		Column descriptionColumn = new Column();
		descriptionColumn.setName("description");
		descriptionColumn.setDbName("description");
		descriptionColumn.setType("String");
		descriptionColumn.setDbType("VARCHAR");

		columns.add(idColumn);
		columns.add(nameColumn);
		columns.add(birthDateColumn);
		columns.add(descriptionColumn);
		table.setColumns(columns);
		String out = templateService.freemarkerDo("src/main/resources/mybatis/mapper/MapperTemplate.xml", "com.hyman", "com.hyman", table);
		System.err.println(out);
	}

}
