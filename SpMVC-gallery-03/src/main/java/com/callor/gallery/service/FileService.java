package com.callor.gallery.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	
	// single file upload
	public String fileUp(MultipartFile file) throws Exception;
	
	// multi file upload
	public List<String> filesUp(MultipartHttpServletRequest files);

}
