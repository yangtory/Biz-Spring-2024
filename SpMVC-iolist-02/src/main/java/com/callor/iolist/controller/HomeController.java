package com.callor.iolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.iolist.persistance.IolistDao;

@Controller
public class HomeController {
	private final IolistDao iolistDao;
	public HomeController(IolistDao iolistDao) {
		this.iolistDao = iolistDao;
	}
	
	/*
	 * @Autowired Spring 에서 자동으로 dependency 를 주입하기 위한 설정
	 * 이 설정이 부착된 method 는 프로젝트가 시작될때 자동으로
	 * 실행되는 성질
	 */
	@Autowired
	public void create_table() {
		this.iolistDao.create_iolist_table(null);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {	
		return "layout";
	}
	
}
