package com.hyman.mybatis;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.hyman.mybatis.model.Table;

public class GeneratorTest {
	
	@Test
	public void TestGenerator() throws SQLException, IOException{
		String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull";
		String user="test";
		String password="test";
		
		Generator generator=new Generator("_","test",url,user,password);
		List<Table> tables=generator.getTablesFromMetaData();
		for(Table table : tables){
			generator.generateClass("src/test/java/com/hyman/mybatis/entity","com.hyman.mybatis.entity",table);
		}
	}
	
}
