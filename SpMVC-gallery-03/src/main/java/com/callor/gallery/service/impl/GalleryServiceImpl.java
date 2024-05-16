package com.callor.gallery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.callor.gallery.config.QualifierConfig;
import com.callor.gallery.models.GalleryVO;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.GalleryService;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	protected final FileService fileService;
	public GalleryServiceImpl(
			@Qualifier(QualifierConfig.SERVCIE.FILE_SERVICE_V1)FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@Override
	public List<GalleryVO> SelectAll() {
		return null;
	}

	@Override
	public int createGallery() {
		return 0;
	}

}
