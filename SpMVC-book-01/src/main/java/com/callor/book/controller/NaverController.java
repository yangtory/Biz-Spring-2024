package com.callor.book.controller;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping(value ="/naver")
public class NaverController {
	
	private final RestTemplate restTemplate;
	
	public NaverController(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value={"/", ""}, method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	/*
	 * ViewResolver 를 통하지 않고
	 * return 되는 값을 그대로 Response 하라 라는 의미
	 */
	@ResponseBody
	@RequestMapping(value="/name", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String home(String name) {
		return "홍길동";
	}

	@ResponseBody
	@RequestMapping(value="/books/{title}",method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String book(@PathVariable(name="title") String title) {
		String KEY_Naver_Client_Id = "X-Naver-Client-Id";
		String KEY_Naver_Client_Secret = "X-Naver-Client-Secret";
		
		String VALUE_Naver_Client_Id = "igkxmxzXZVDjOhr2XIhj";
		String VALUE_Naver_Client_Secret = "EmdIAQNUc5";
		
		// Http Header 에 ID 와 SECRET 값을 설정하기
		// org.spring...HttpHeaders import
		HttpHeaders headers = new HttpHeaders();
        headers.set(KEY_Naver_Client_Id, VALUE_Naver_Client_Id);
        headers.set(KEY_Naver_Client_Secret, VALUE_Naver_Client_Secret);
        
		HttpEntity<String> params = new HttpEntity<>(headers);
		
		String naver_book_url = "https://openapi.naver.com/v1/search/book.json?query=" + title;
		
		String result = restTemplate.getForObject(naver_book_url, String.class,params);
	        
	    return result;
	}

}
