package com.callor.gallery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.gallery.models.UserVO;
import com.callor.gallery.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return null;		
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVO userVO) {
		int ret = userService.create_user(userVO);
		log.debug("회원가입 {}", userVO.toString());
		return null;
	}
	
	@RequestMapping(value={"/login","/login/{fail}"}, method=RequestMethod.GET)
	public String login(
			@PathVariable(value="fail", required = false) String fail,
			HttpSession httpSession ) {
		
		if(fail == null || fail.isBlank()) {
			// 다시 로그인으로 들어왔을때 SPRING_SECURITY_LAST_EXCEPTION 이거 지워버려
			httpSession.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		}	
		return "user/login";		
	}
	
	
}
