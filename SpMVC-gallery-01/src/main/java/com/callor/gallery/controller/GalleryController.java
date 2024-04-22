package com.callor.gallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.gallery.controller.models.GalleryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {
		return "gallery/input";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(GalleryVO galleryVO) {
		log.debug(galleryVO.toString());
		return "gallery/input";
	}

}
