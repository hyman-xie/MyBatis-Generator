MyBatis Generator Maven plugin
=============================

A maven plugin to generate entity classes and xmls for mybatis3.

Please check following steps:

1.add this maven plugin into your pom xml

	<build>
		<plugins>
			<plugin>
				<groupId>com.hyman</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>0.9.0-SNAPSHOT</version>
				<executions>
					<execution>
						<goals>
							<goal>mybatisGenerator</goal>
						</goals>
						<configuration>
							<jdbcUrl>jdbc:mysql://localhost:3306/test</jdbcUrl>
							<jdbcUserName>test</jdbcUserName>
							<jdbcPassword>test</jdbcPassword>
							<entityPackage>com.hyman.model</entityPackage>
							<entityOutputDirectory>src/main/java/com/hyman/model</entityOutputDirectory>
							<mapperPackage>com.hyman.mapper</mapperPackage>
							<mapperOutputDirectory>src/main/java/com/hyman/mapper</mapperOutputDirectory>
							<mapperXmlOutputDirectory>src/main/resources/mybatis/hyman/mapper</mapperXmlOutputDirectory>
							<tablePrefix>test_</tablePrefix>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
2. "mvn install" your maven project 
