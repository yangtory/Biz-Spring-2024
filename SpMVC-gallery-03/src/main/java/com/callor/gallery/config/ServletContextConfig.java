package com.callor.gallery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// bean-context.xml 역할 
@Configuration

// annotation-driven 을 대신하는 설정, mvc 패턴으로 작성하겟음 
@EnableWebMvc

@ComponentScan(basePackages = {"com.callor.gallery.controller"})
public class ServletContextConfig implements WebMvcConfigurer {
	// interface 를 상속받았지만 unimplements 경고가 뜨지 않는다
	// 위 두 annotation 때문에	

	// resource-mapping 을 대신하는 코드
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// static 안에 있는 애들은 rendering 하지말고 넘기렴
		registry.addResourceHandler("/static/**")
				.addResourceLocations("/static/");
		
		// css 를 요청하면 static/css 를 보여줘
		registry.addResourceHandler("/css/**")
				.addResourceLocations("/static/css/");
		
		registry.addResourceHandler("/js/**")
				.addResourceLocations("/static/js/");
		
		// images 에서 요청하면 첫번째에서 찾아보고 없음 두번 째 
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:///app/upload/","/static/images/");
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	// view 를 렌더링 하는 bean 생성, jsp 핸들링 
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
