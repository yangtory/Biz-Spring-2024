package com.callor.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.hello.models.UserDto;
import com.callor.hello.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/get", method=RequestMethod.GET)
	public String getUser() {
		return null;
	}
	
	/*
	 * GET/user/join
	 * POST/user/join
	 * method 를 생성하여 회원가입 화면을 띄우고
	 * username, password, name, email, tel 을 입력받고
	 * 회원가입을 실행하여 서버로 데이터가 전달되는지 확인하기  
	 */
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return null;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserDto userDto, Model model) {
		log.debug("form에서 받은 데이터 {}",userDto.toString());
		model.addAttribute("USER",userDto);
		return null;
	}
}
