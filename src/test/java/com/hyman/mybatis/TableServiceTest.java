package com.hyman.mybatis;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.hyman.mybatis.model.Column;
import com.hyman.mybatis.model.Table;
import com.hyman.mybatis.service.TableService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/config.xml")
public class TableServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testGenerator() throws SQLException, IOException{
		TableService tableService=new TableService();
		tableService.setTablePrefix("test_".toUpperCase());
		List<Table> tables=tableService.getTables(dataSource.getConnection());
		Assert.isTrue(tables.size()==1);
		Table table=tables.get(0);
		Assert.isTrue(table.getDbName().equals("TEST_PRODUCT"));
		Assert.isTrue(table.getName().equals("Product"));
		Assert.isTrue(table.getColumns().size()==8);
		String[] columns=new String[]{"id","name","status","isHot","createUser","createTime","modifyUser","modifyTime"};
		String[] dbColumns=new String[]{"id","name","status","is_hot","create_user","create_time","modify_user","modify_time"};
		for(int i=0;i<table.getColumns().size();i++){
			Column column=table.getColumns().get(i);
			Assert.isTrue(column.getName().compareTo(columns[i])==0);
			Assert.isTrue(column.getDbName().compareTo(dbColumns[i].toUpperCase())==0);
		}
	}
	
}
