package com.callor.gallery.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.models.ImageVO;
import com.callor.gallery.service.FileUploadService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

	
	private final String folder;
	// Spring 에서 제공하는 프로젝트 context 정보를 담고있는 객체
	private final ServletContext context;
	
	public FileUploadServiceImpl(ServletContext context) {
		super();
		this.context = context;
		// tomcat 폴더가 아닌 server 의 로컬 스토리지의 임의의 폴더
		folder = "/app/upload";
	}

	@Override
	public String fileUpload(MultipartFile file) throws Exception {
		String originalFileName = file.getOriginalFilename();
		
		if(originalFileName.isEmpty()) {
			return null;
		}
		/*
		 * context.getRealPath("path") 이 method 는
		 * 실행중인 Server 의 work folder를 가르킨다
		 * Server 가 정상적으로 실행되는 동안은 문제없이 이 폴더에
		 * 파일을 저장, 읽기 가능하다.
		 * 하지만 Tomcat WorkDirectory clean 을 실행하면 폴더가 삭제됨
		 * 절대 이 폴더에 저장하면 안됨
		 */
		// 프로젝트 contextRoot/static/upload 폴더 정보 지정하기
		// 실제론 프로젝트 /wepapp/static/upload 폴더를 가르킨다
//		String folder = context.getRealPath("/static/upload");
//		log.debug("업로드 폴더 {} ", folder);
		
		// 문자열로 된 폴더 정보를 Java 에서 사용하기 위해 객체로 변환하기
		File path = new File(folder);
		
		// 업로드할 폴더 검사, 없으면 생성
		if(!path.exists()) {
			// mkdir : 폴더 개수에 상관없이 생성
			path.mkdirs();
		}
		
		// 같은 이름의 파일을 업로드하여 기존의 업로드된 파일 변형 공격을 막기위해
		// UUID 를 부착하여 파일 이름을 변형
		String uuid = UUID.randomUUID().toString();
		String upLoadFileName = String.format("%s-%s", uuid,originalFileName);
		
		// 폴더이름 + 파일이름을 결합, 업로드할 파일 정보 생성하여
		// upLoadFile 객체에 저장
		File uploadFile = new File(folder, upLoadFileName);
		
		// file 을 upLoadFile 정보에 복사(업로드 실행)
		file.transferTo(uploadFile);
		
		// 실제 저장된 파일의 이름을 return
		return upLoadFileName;
	}

	@Override
	public List<ImageVO> filesUpload(MultipartHttpServletRequest files) throws Exception {
		
		// 업로드된 멀티 파일을 List type 의 MultiPartFile 로 분해하기
		// 업로드된 멀티 파일을 추출하기 위해서 files.getFiles("이름") 를 사용하는데
		// 이름은 form 의 input 태그에 붙여진 이름을 사용한다.
		List<MultipartFile> result = files.getFiles("image_files");
		
		List<ImageVO> resultImages = new ArrayList<>();
		// single file 업로드를 사용해서 return 해준 파일 이름을 List 로 만듬 
		for(MultipartFile f : result) {
			String resName = this.fileUpload(f);
			//origin name과 변형된 name 을 같이 
			resultImages.add(
					ImageVO.builder()
					.i_id(UUID.randomUUID().toString())
					.i_origin_image(f.getOriginalFilename())
					.i_up_image(resName).build());
		}
		return resultImages;
	}

}
