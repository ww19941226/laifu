莱福智能小区环境搭建

2016/9/7 by raindrops
系统名称：莱福智能小区
采用框架:Spring SpringMVC Hibernate

文档目录


1.导入jar包：

	1.1 hibernate jar包
	
		hibernate-commons-annotations-4.0.1.Final.jar
		hibernate-core-4.1.0.Final.jar
		hibernate-ehcache-4.1.0.Final.jar
		hibernate-jpa-2.0-api-1.0.1.Final.jar
		hibernate-proxool-4.1.0.Final.jar
		hibernate-validator-4.2.0.Final.jar
		hibernate-validator-annotation-processor-4.1.0.Final.jar
		
	1.2 Spring jar包
	
		org.springframework.aop-3.1.1.RELEASE.jar
		org.springframework.asm-3.1.1.RELEASE.jar
		org.springframework.aspects-3.1.1.RELEASE.jar
		org.springframework.beans-3.1.1.RELEASE.jar
		org.springframework.context-3.1.1.RELEASE.jar
		org.springframework.context.support-3.1.1.RELEASE.jar
		org.springframework.core-3.1.1.RELEASE.jar
		org.springframework.expression-3.1.1.RELEASE.jar
		org.springframework.jdbc-3.1.1.RELEASE.jar
		org.springframework.orm-3.1.1.RELEASE.jar
		org.springframework.test-3.1.1.RELEASE.jar
		org.springframework.transaction-3.1.1.RELEASE.jar
		org.springframework.web-3.1.1.RELEASE.jar
		org.springframework.web.servlet-3.1.1.RELEASE.jar
		
		com.springsource.org.codehaus.jackson-1.4.2.jar
		com.springsource.org.codehaus.jackson.mapper-1.4.2.jar
		com.springsource.org.junit-4.7.0.jar
		com.springsource.org.quartz-1.6.2.jar
		
	1.3 数据库相关jar包
	
		proxool-0.9.1.jar
		proxool-cglib.jar
		mysql-connector-java-5.1.10.jar
		
	1.4 json jar包
		
		ezmorph-1.0.3.jar
		json-lib-2.1-jdk15.jar
	
	1.5 其他依赖jar包
	
		antlr-3.3-complete.jar
		aopalliance-1.0.jar
		aspectjrt.jar
		aspectjweaver.jar
		cglib-nodep-2.1_3.jar
		classmate-0.5.4.jar
		commons-beanutils-1.7.0.jar
		commons-chain-1.2.jar
		commons-codec-1.3.jar
		commons-collections-3.2.1.jar
		commons-digester-2.0.jar
		commons-fileupload-1.2.1.jar
		commons-io-1.3.2.jar
		commons-lang-2.3.jar
		commons-logging-1.0.4.jar
		commons-logging-api-1.1.jar
		commons-pool.jar
		commons-validator-1.3.1.jar
		dom4j-1.6.1.jar
		ehcache-core-2.4.3.jar
		javassist-3.15.0-GA.jar
		jboss-logging-3.1.0.CR2.jar
		jboss-transaction-api_1.1_spec-1.0.0.Final.jar
		jstl.jar
		log4j-1.2.16.jar
		slf4j-api-1.6.1.jar
		slf4j-log4j12-1.6.1.jar
		standard.jar
		validation-api-1.0.0.GA.jar
		
2.package详情：
	
	com.laifu.common 							常量类
	com.laifu.common.dao						通用dao
	com.laifu.common.dao.impl					通用dao实现
	com.laifu.common.exception					异常类
	com.laifu.common.pagination					分页类
	com.laifu.common.service					通用service接口
	com.laifu.common.service.impl				通用service实现类
	com.laifu.common.utils						通用工具类
	com.laifu.module.dao						dao接口
	com.laifu.module.dao.impl					dao实现类
	com.laifu.module.entity						实体类
	com.laifu.module.service					service接口
	com.laifu.module.service.impl				service实现类
	com.laifu.module.support.editor				SpringMVC验证的绑定类
	com.laifu.module.web.controller				controller类
	com.laifu.module.web.filter					过滤器
	com.laifu.module.web.tag					自定义jstl标签类
	com.laifu.module.web.validator				验证类
	
3.文件夹
	
	doc |										命令文件
	   sql|										sql命令文件
	
	WEB-INF|
		  css   |								css样式文件
		  images|								图片
		  js	|								JavaScript文件
		  lib	|								jar 包
		  jsp	|								jsp页面
		  	   inc		|						头文件
		  	   people	|						people类相关视图
		  	   usertype |						usertype类相关视图
		  	   error_all.jsp					错误的全局处理页面
		  	   index.jsp						主页
		  tld	|								自定义jstl标签库
		  
		  
4.配置文件

	ehcache_hibernate.xml						hibernate开启缓存的配置文件
	log4j.properties							log4j配置文件
	messages.properties							验证的国际化文件
	resources.properties						jdbc连接池的配置文件
	spring-config.xml							spring配置
	spring-servlet.xml							springMVC配置