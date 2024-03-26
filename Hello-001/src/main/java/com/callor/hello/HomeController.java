package com.callor.hello;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 어노테이션
public class HomeController { // HomeContoller = router 
	
	
	// '/' 로 요청이 되면 이렇게 해
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home"; // home.jsp 렌더링 해
	}
	
}
