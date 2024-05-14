package com.callor.gallery.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/*
 * @Configuration
 * 지금부터 이 클래스는 ContextBeanConfig 설정을 하는 클래스다 라는 선언
 * xml 과같은 역할을 한다
 */
@Configuration
public class RootContextConfig {
	
	// name 은 호환성을 위해 bean 이름 설정함
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getFileResolver() throws IOException {
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		// 한 개의 파일 용량 제한하기
		resolver.setMaxUploadSize( 1024 * 1024 * 2);
		resolver.setMaxUploadSizePerFile(1024 * 1024 * 20);
		
		// 파일을 업로드할때 임시로 저장할 폴더 지정, 선택사항
		resolver.setUploadTempDir(new FileSystemResource("c:/temp"));
		// 파일이름이 한글일 경우 깨지는 것 방지
		resolver.setDefaultEncoding("UTF-8");
		
		return resolver;
	}

}
