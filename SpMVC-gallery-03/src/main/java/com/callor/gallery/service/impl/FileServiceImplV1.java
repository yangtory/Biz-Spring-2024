package com.callor.gallery.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.config.QualifierConfig;
import com.callor.gallery.models.ImageVO;
import com.callor.gallery.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(QualifierConfig.SERVCIE.FILE_SERVICE_V1)
public class FileServiceImplV1 implements FileService {
	/*
	 * 편의상 FileServiceImplV1 을 FileServiceV2 에서 상속했다
	 * V1 과 V2 는 FileService 인터페이스를 implements 하고있다
	 * 두 클래스는 모두 FileService 인터페이스의 자손이다
	 * V1 에서 구현된 코드를 V2 에서 확장, 일부변경 하여 사용하고자 한다
	 * 이 때 V1 과 V2 는 모두 upLoadPath 변수를 사용해야 한다
	 * 이 경우 변수를 private 로 선언하면 V2 에서 다시
	 * 생성자를 통해 upLoadPath 변수를 주입 받아야한다
	 * 상속 해주려는 클래스와 상속 받는 클래스에서
	 * 공통으로 사용하는 변수는 protected 로 선언해야 한다
	 *  
	 * 유의해야 할 것!
	 * 생성자는 상속되지 않는다
	 */
	protected final String upLoadPath;

	public FileServiceImplV1(String upLoadPath) {
		super();
		this.upLoadPath = upLoadPath;
		log.debug("업로드 폴더 {} ", upLoadPath);
	}

	@Override
	public String fileUp(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		
		if(fileName.isBlank()) {
			return null;
		}
		File path = new File(upLoadPath);
		
		// 업로드할 폴더 없으면 생성
		if(!path.exists()) {
			/*
			 * path.mkdir(), path.mkdirs()
			 * 폴더를 생성하는 method 임
			 * mkdir() : 한 개의 폴더만 생성 : app/upload 폴더를 생성할 경우
			 * 			app 폴더가 없으면 오류가 발생
			 * mkdirs() : 여러 경로의 폴더 생성하기 : app/upload 폴더를 생성할 경우
			 * 			app 폴더가 없으면 그거부터 만듬
			 */	
			path.mkdirs();
		}
		
		String uuid = UUID.randomUUID().toString();
		// 원래 파일 앞에 uuid 부착, injection 공격 대비
		fileName = String.format("%s-%s", uuid, fileName);
		
		File upload = new File(upLoadPath, fileName);
		
		// multipart 클래스에 정의된 파일 전송 method
		file.transferTo(upload);
		
		// DB 에 저장하기 위해 변경된 파일 이름 return
		return fileName;
	}

	@Override
	public List<ImageVO> filesUp(MultipartHttpServletRequest files) throws Exception {
		/*
		 * view 의 form input[type='file'] 태그의 name 속성("files")을 통해
		 * 파일 리스트 추출
		 */
		List<MultipartFile> fileList = files.getFiles("files");
		List<ImageVO> resultFiles = new ArrayList<>();
		
		for(MultipartFile file : fileList) {
			String resultName = this.fileUp(file);
			String originName = file.getOriginalFilename();
			resultFiles.add(
					ImageVO.builder()
					.i_id(UUID.randomUUID().toString())
					.i_up_image(resultName)
					.i_origin_image(originName)
					.build()					
					);			
		}
		return resultFiles;
	}

}
