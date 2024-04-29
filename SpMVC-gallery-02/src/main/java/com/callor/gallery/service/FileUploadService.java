package com.callor.gallery.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.models.ImageVO;

public interface FileUploadService {
	
	/*
	 * 파일객체 한 개를 전달받아 
	 * 해킹에 대비해 파일 이름을 변형하고
	 * 변형된 이름으로 서버의 폴더에 업로드(저장) 하고
	 * 변형된 이름을 return 하는 일을 수행
	 * 
	 * throws Exception : exception 이 발생하면 호출한 곳으로 되돌려보냄
	 */
	public String fileUpload(MultipartFile file) throws Exception;
	
	/*
	 * 여러개의 파일을 업로드 하고
	 * 변형된 파일 이름 리스트를 return
	 * 
	 * 위 method 를 여러번 호출해서 사용한다.
	 */
	public List<ImageVO> filesUpload(MultipartHttpServletRequest files) throws Exception;

}
