package com.callor.gallery.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String fileUpload(MultipartFile file) throws Exception {
		if(file.getOriginalFilename().isEmpty()) {
			return null;
		}
		String folder = "upload";
		File path = new File(folder);
		// 폴더 있냐 없음 만들어라
		if(!path.exists()) {
			path.mkdirs();
		}
		
		File uploadFile = new File(folder, file.getOriginalFilename());
		file.transferTo(uploadFile);
		
		return null;
	}

	@Override
	public List<String> filesUpload(MultipartHttpServletRequest files) throws Exception {
		return null;
	}

}
