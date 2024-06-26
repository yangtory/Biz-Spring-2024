package com.callor.hello.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.hello.models.UserDto;
import com.callor.hello.service.UserService;
import com.callor.hello.service.impl.UserServiceImpl;

/*
 * Spring 의 routing
 * Spring 에서는 Routing 을 Controller 라고 한다.
 * Spring class 에 @Controller 를 부착하면 이 클래스가 Routing 을 수행하게 된다
 * Class 의 method 들이 각각의 Routing 이 되어 응답을 처리한다
 * 
 * 이 때 각 method 에는 @RequestMapping 과 RequestMethod 를 부착하여
 * 각각의 Routing 경로와 응답 method 를 지정한다
 * 
 * @RequestMapping 을 Controller 에 부착하면
 * 대표 Routing 이 되고, 각 method 의 RequestMapping 과 함께
 * 융합된 응답 Mapping 이 되고 Routing 을 수행한다
 * 
 * 이 클래스는 http://localhost:8080/hello/user/login 과 같은
 * 요청을 처리하는 Routing 이 된다.
 */

@Controller //지금부터 너는 컨트롤러야
@RequestMapping(value="/user")
public class UserController {
	/*
	 * UserService 객체를 UserController 의
	 * 여러 method 에서 사용하기 위하여 선언하고
	 * 생성자에서 생성하기
	 */
	private final UserService userService;
	/*
	 * 생성자에 아무 준비가 되지않은 매개변수를 선언하는 것
	 * Spring 아
	 * 미리 준비돼 있는 userService bean 을 나에게 달라
	 * 나에게 객체를 주입해 달라(DI : Dependency Injection, 의존성 주입)
	 */
	public UserController(UserService userService) {
		// 기존의 Java 방식으로 class 를 객체로 생성하기
		// userService = new UserServiceImpl();
		
		// DI 로 받은 userService 객체를 사용하기
		this.userService=userService;

	}

	// return 에 null 값이 있으면 경로와 동일한 .jsp 를 찾는다
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String  login() {
		/*
		 * Router 의 method 가 문자열을 return 하면
		 * WEB-INF/view 폴더에서 `문자열.jsp` 파일을 찾아서
		 * rendering 한 후 Response 를 한다.
		 * 
		 * 그런데 method 에서 null 을 return 하면
		 * 자신이 요청받은 Request Routing 주소를 문자열로 return 한 것과
		 * 똑같이 작동한다
		 * 즉, return "user/login"
		 */
	return null;
}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username, String password, Model model) {
		// model.addAttribute : username 이라는 변수 하나 만들어서 그 값을 "username"에 넣어라
		model.addAttribute("username",username);
		model.addAttribute("password",password);
		// return user/login
		return null;
	}
	
	/*
	 * UserService.getUser() method 를 호출하여
	 * 샘플 사용자 정보를 받아서 jsp 파일로 보내주기
	 */
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		// UserService 를 UserController 의 여러 method 에서
		// 사용을 해야 한다면 어떻게?
//		UserService userService = new UserServiceImpl();
		UserDto user = userService.getUser();
		model.addAttribute("USER",user);
		return null;
	}
	
//	@RequestMapping(value="join", method=RequestMethod.POST)
//	public String join(String username, String password, String name, String email, String tel) {
//		return null;
//	}
	// 위 와 같음
	@RequestMapping(value="join", method=RequestMethod.POST)
	// Dto를 매개변수로 받으면 같은이름을 모두 받을수 있다
	public String join(UserDto userDto, Model model) {
		model.addAttribute("USER",userDto);
		return null;
	}
}
