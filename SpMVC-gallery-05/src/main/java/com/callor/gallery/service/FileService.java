package com.callor.gallery.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.models.ImagesVO;

public interface FileService {
	
	// single file
	public String fileUp(MultipartFile file);
	
	// multi file
	public List<ImagesVO> filesUp(MultipartHttpServletRequest files);

}
