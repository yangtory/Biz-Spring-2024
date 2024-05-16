package com.callor.gallery.service.impl;

import org.springframework.stereotype.Service;

import com.callor.gallery.config.QualifierConfig;

/*
 * 프로젝트에서 한 개의 interface 를 implements 하여 다수의 클래스를 정의하는 경우가 있음
 * 이럴 때 사용하는 곳에서 interface 를 사용하여 객체를 선언하고
 * 생성자나 setter 등을 통해 객체(bean)를 주입받는다
 * 이 때 한 개의 interface 를 implements 한 객체가 2개 이상일 경우
 * Spring DI 는 어떤 클래스를 주입해야 할지 모른다
 * Spring DI 에게 알려주기 위해 Service Annotation 에 이름을 부여하고
 * 주입받는 곳에서 @Qualifier 를 사용하여 명시해줘야 함  
 */
@Service(QualifierConfig.SERVCIE.FILE_SERVICE_V2)
public class FileServiceImplV2 extends FileServiceImplV1 {
	
	/*
	 * V1 에서 생성자를 통하여 upLoadPath 변수를 주입받고 있다
	 * upLoadPath 변수는 V1 과 V2 에서 공통으로 사용하는 변수다
	 * 이 경우 상속받은 클래스에서 반드시 생성자를 구현하고
	 * 생성자를 통해 upLoadPath 변수를 주입받아야 한다
	 */
	public FileServiceImplV2(String upLoadPath) {
		super(upLoadPath);
		// TODO Auto-generated constructor stub
	}

}
