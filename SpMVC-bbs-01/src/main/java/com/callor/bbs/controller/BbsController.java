package com.callor.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/bbs")
public class BbsController {
	
	@RequestMapping(value={"/"," "}, method=RequestMethod.GET)
	public String home() {
		return "bbs/free";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return null;
	}

}
