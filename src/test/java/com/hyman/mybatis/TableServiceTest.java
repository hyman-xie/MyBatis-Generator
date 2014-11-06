package com.hyman.mybatis;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.hyman.mybatis.model.Table;
import com.hyman.mybatis.service.TableService;

public class TableServiceTest {
	
//	@Test
	public void testGenerator() throws SQLException, IOException{
		String url="jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull";
		String user="test";
		String password="test";
		
		TableService tableService=new TableService();
		List<Table> tables=tableService.getTablesFromMetaData(url, user, password);
		for(Table table : tables){
			System.err.print(table);
		}
	}
	
}
