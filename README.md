## MyBatis 3.5.2 Annotations ORM Mapping Example


### Used technology stack in this example

	1. Java-12 or OpenJDK 11, 12
	2. Apache Maven 3.5.4
	3. MySql 8.0.27
	4. PostgreSQL 12.9-1
	5. Oracle 19.3 (19c)


### This example work with MySQL, PostgreSQL, and Oracle

	- Only modify the SqlMapConfig.xml to work with your database user/schema.
	- Create a DB user/schema and mention in above config xml file before run this example with oracle 19c/PostgreSQL/MySQL
	

### Maven initial setup

	$ mvn dependency:tree
	$ mvn eclipse:eclipse


### Run Directly the application

	$ MyBatisAnnotationsApp.java


### Build application jar or war

	$ mvn clean package


### For more details

	https://mybatis.org/mybatis-3/index.html
	
Note: db stored procedure call may have some issues.	
