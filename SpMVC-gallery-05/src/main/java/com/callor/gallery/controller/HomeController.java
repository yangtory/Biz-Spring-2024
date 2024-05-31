package com.callor.gallery.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.models.galleryVO;
import com.callor.gallery.service.GalleryService;
import com.callor.gallery.utils.Names;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	private final GalleryService galleryService;

	public HomeController(GalleryService galleryService) {
		super();
		this.galleryService = galleryService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	/*
	 * single file 을 controller 에서 받을때는 MultipartFile 을 사용하여 받고
	 * 이 때는 @RequestParam("name") 속성을 붙여준다
	 * 
	 *  하지만 Multi File 을 Contorller 에서 받을때는
	 *  MultipartHttpServletRequest 를 사용하여 받고
	 *  이 때는 절대 @RequestParam() 속성을 사용하면 안된다
	 */
//	@RequestMapping(value="/", method=RequestMethod.POST)
	@PostMapping(value = "/")
	public String home(galleryVO galleryVO, MultipartHttpServletRequest files) {
		log.debug("gallery {} ", galleryVO.toString());
		
		// files : form 의 input type="file" 속성의 name 속성과 같다
		List<MultipartFile> fileList = files.getFiles(Names.FILES);
		for(MultipartFile file: fileList) {
			log.debug("file {} ", file.getOriginalFilename());
		}
		
		galleryService.createGallerys(galleryVO, files);		
		return "home";
	}
}
