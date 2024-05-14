package com.callor.gallery.config;

import java.io.IOException;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages = {"com.callor.gallery.dao"})
public class MyBatisContextConfig {
	
	/*
	 * java 코드에서 /WEB-INF 또는 src/main/resources 등의 폴더에 있는
	 * xml 등의 파일에 접근하기 위한 도구, 내 어플리케이션의 모든 정보를 알수 잇음
	 * configuration 에서 templete 이 필요 없다
	 */
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
	public SqlSessionFactoryBean sessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		/*
		 * Tomcat 서버의 저장소에서 ~~~/WEB-INF/spring/mapper 폴더에서
		 * *-mapper.xml 파일들의 정보를 가져와서 MapperLocations 속성에 등록
		 * server 폴더에 있는거 쓰기, 경로에 폴더,파일이 없을수 있어서
		 * try-catch 로 묶어주기
		 */
		try {
			bean.setMapperLocations(context.getResources("/WEB-INF/spring/mapper/*-mapper.xml"));
			
			// ~~/src/main/resources 폴더의 mapper 폴더에서 xml 을 참조하고 싶을때
			// classpath 서버에 있는 ~~/src/main/resources 이 폴더 지정하는거
			// context.getResources("classpath:/**/mapper/*-mapper.xml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setDataSource(this.ds());
		bean.setTypeAliasesPackage("com.callor.gallery.models");
		
		return bean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(this.ds());
		return manager;
	}

}
