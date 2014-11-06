package com.hyman.maven.plugins.mybatis;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.dependency.AbstractDependencyMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import com.hyman.mybatis.generator.EntityGenerator;
import com.hyman.mybatis.generator.MapperGenerator;
import com.hyman.mybatis.generator.MapperXmlGenerator;
import com.hyman.mybatis.model.Table;
import com.hyman.mybatis.service.TableService;
import com.hyman.mybatis.service.TemplateService;

@Mojo(name = "mybatisGenerator", requiresDependencyResolution = ResolutionScope.COMPILE,defaultPhase = LifecyclePhase.PROCESS_SOURCES, threadSafe = true)
public class MyBatisGeneratorMojo extends AbstractDependencyMojo {

	@Parameter(defaultValue = "com.hyman.model.entity")
	private String entityPackage;
	
	@Parameter(defaultValue = "${project.basedir}/src/main/java/com/hyman/model/entity")
	private String entityOutputDirectory;
	
	@Parameter(defaultValue = "com.hyman.dao.mapper")
	private String mapperPackage;
	
	@Parameter(defaultValue = "${project.basedir}/src/main/java/com/hyman/dao/mapper")
	private String mapperOutputDirectory;
	
	@Parameter
	private String mapperXmlTemplatePath;
	
	@Parameter(defaultValue = "${project.basedir}/src/main/resources/mybatis/mapper")
	private String mapperXmlOutputDirectory;
	
	@Parameter
	private String jdbcUrl;
	
	@Parameter
	private String jdbcUserName;
	
	@Parameter
	private String jdbcPassword;
	
	@Override
	protected void doExecute() throws MojoExecutionException, MojoFailureException {
		if(jdbcUrl==null || jdbcUserName==null || jdbcPassword==null){
			throw new MojoExecutionException("Please config jdbc parameters!");
		}
		try {
			TableService tableService=new TableService();
			TemplateService templateService=new TemplateService();
			EntityGenerator entityGenerator=new EntityGenerator();
			MapperXmlGenerator mapperXmlGenerator=new MapperXmlGenerator();
			MapperGenerator mapperGenerator=new MapperGenerator();
			
			List<Table> tables=tableService.getTablesFromMetaData(jdbcUrl, jdbcUserName, jdbcPassword);
			for(Table table : tables){
				String template=templateService.freemarkerDo(mapperXmlTemplatePath, entityPackage, mapperPackage,table);
				entityGenerator.generateClass(entityOutputDirectory, entityPackage, table);
				mapperXmlGenerator.generateTemplate(mapperXmlOutputDirectory, template, table);
				mapperGenerator.generateMapper(entityPackage, mapperPackage, mapperOutputDirectory, table);
			}
		} catch (Exception e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getEntityOutputDirectory() {
		return entityOutputDirectory;
	}

	public void setEntityOutputDirectory(String entityOutputDirectory) {
		this.entityOutputDirectory = entityOutputDirectory;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public String getMapperOutputDirectory() {
		return mapperOutputDirectory;
	}

	public void setMapperOutputDirectory(String mapperOutputDirectory) {
		this.mapperOutputDirectory = mapperOutputDirectory;
	}

	public String getMapperXmlTemplatePath() {
		return mapperXmlTemplatePath;
	}

	public void setMapperXmlTemplatePath(String mapperXmlTemplatePath) {
		this.mapperXmlTemplatePath = mapperXmlTemplatePath;
	}

	public String getMapperXmlOutputDirectory() {
		return mapperXmlOutputDirectory;
	}

	public void setMapperXmlOutputDirectory(String mapperXmlOutputDirectory) {
		this.mapperXmlOutputDirectory = mapperXmlOutputDirectory;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUserName() {
		return jdbcUserName;
	}

	public void setJdbcUserName(String jdbcUserName) {
		this.jdbcUserName = jdbcUserName;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
	
}
