package com.callor.gallery.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.models.galleryVO;

public interface GalleryService {
	
	public int createGallerys(galleryVO galleryVO, MultipartHttpServletRequest files);

}
