package com.hyman.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.hyman.mybatis.model.Column;
import com.hyman.mybatis.model.Table;

public abstract class ABaseTest {

	protected Table generateTable(){
		Table table = new Table();
		table.setName("Hyman");
		table.setDbName("hyman");
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
		return table;
	}
	
}
