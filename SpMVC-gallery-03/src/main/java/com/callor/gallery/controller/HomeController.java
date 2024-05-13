package com.callor.gallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.gallery.dao.GalleryDao;


@Controller
public class HomeController {
	
	private final GalleryDao galleryDao;	

	public HomeController(GalleryDao galleryDao) {
		super();
		this.galleryDao = galleryDao;
		this.galleryDao.create_table();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home( Model model) {
		return "home";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		
		return "input";
	}
	
}
