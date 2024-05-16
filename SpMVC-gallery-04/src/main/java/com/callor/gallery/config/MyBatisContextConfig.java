package com.callor.gallery.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = "com.callor.gallery.dao")
public class MyBatisContextConfig {

	private final ApplicationContext context;

	public MyBatisContextConfig(ApplicationContext context) {
		super();
		this.context = context;
	}

	@Bean
	public DataSource ds() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/galleryDB2");
		ds.setUsername("root");
		ds.setPassword("!Biz8080");
		return ds;
	}

	@Bean
	public SqlSessionFactoryBean factoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		// ~src/main/resource 폴더에 mapper/*-mapper.xml 을 찾아라
		Resource resource = context.getResource("classpath:/**/mapper/*-mapper.xml");
		bean.setMapperLocations(resource);
		
//		try {
//			bean.setMapperLocations(context.getResources("/WEB-INF/spring/mapper/*-mapper.xml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		bean.setDataSource(this.ds());
		bean.setTypeAliasesPackage("com.callor.gallery.model");
		return bean;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		// PlatformTransactionManager manager = new DataSourceTransactionManager();
		// ((DataSourceTransactionManager)manager).setDataSource(this.ds());
		/*
		 * manager 객체는 Platform...Manager 인터페이스를 사용하여 선언하였다.
		 * 이 인터페이스는 다양한 Transaction 을 실행하기 위한 설계도 이다.
		 * 
		 * DataSource...Manager 는 Platform...Manager 인터페이스를 사용하여
		 * 구현된 구현체 클래스이다.
		 * 
		 * DataSource...Manager 는 Platform...Manager 인터페이스를 implements 했지만
		 * 자체적으로 코드를 구현하면서 여러가지 method 를 별도로 가지고 있다.
		 * 
		 * 우리는 DataSource...Manager 에게 DataSource(ds()) 를 알려주고
		 * DataSource 차원에서 Transaction 을 수행하도록 하려고 한다.
		 * 그런데 Platform... 에는 DataSource 를 주입하는 method 가 정의되지 않았다.
		 * 결국 Platform... 에는 없지만 DataSource... 에만 있는 setDataSource() method 를
		 * 사용해야 한다.
		 * 
		 * 이럴때 DataSource... type 으로 Casting(강제형변환) 을 수행하야 한다.
		 */
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(this.ds());
		return manager;
	}

}
