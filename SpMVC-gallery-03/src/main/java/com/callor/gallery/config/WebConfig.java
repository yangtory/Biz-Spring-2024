package com.callor.gallery.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * web.xml 의 설정을 대신할 클래스 파일
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// RootContextConfig 클래스를 읽어와서 Class배열로 만들어라, 클래스 참조
		return new Class[] { RootContextConfig.class, MyBatisContextConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletContextConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// "/" 로 요청이 들어오면 내가 받음
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// 한글 필터
		CharacterEncodingFilter encKor = new CharacterEncodingFilter();
		encKor.setEncoding("UTF-8");
		encKor.setForceEncoding(true);
		return new Filter[] { encKor };
	}

}
