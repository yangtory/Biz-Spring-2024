package com.callor.iolist.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.iolist.models.IolistVO;
import com.callor.iolist.models.SearchDto;
import com.callor.iolist.models.UserVO;
import com.callor.iolist.persistance.IolistDao;
import com.callor.iolist.utils.NamesValue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/iolist")
public class IolistController {
	
	private final IolistDao iolistDao;
	public IolistController(IolistDao iolistDao) {
		this.iolistDao = iolistDao;
	}
	
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String home(@ModelAttribute("SEARCH") SearchDto searchDto,Model model) {
		log.debug("pname {},sdate {},edate {}",
				searchDto.getPname(),searchDto.getSdate(),searchDto.getEdate());
		List<IolistVO>iolist = iolistDao.selectSearchAll(searchDto);
		// body 변수에 iolist_home 문자열 담기
		model.addAttribute("BODY","IOLIST_HOME");
		model.addAttribute("IOLIST",iolist);
		model.addAttribute("SEARCH",searchDto);
		return "layout";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model,HttpSession httpSession) {
		/*
		 * HttpSession 에 저장된 session 정보는 type 이 Object 이다
		 * 그래서 실제 상황에서는 필요한 객체 type 으로 Casting(형변환)을 해야한다.
		 * 
		 * float num1 = 10;
		 * int num2 = (int)num1; 
		 */
		// 사용자가 매입매출추가를 했을때 
		// 로그인이 되어있는지 확인 후 error 메세지 전달하는 방법
		UserVO userVO = (UserVO) httpSession.getAttribute(NamesValue.SESSION.USER);
		if(userVO == null) {
			return "redirect:/user/login?error=needs";
		}
		
		// 날짜와 관련된 java 1.8 이전버전의 클래스
		Date today = new Date();
		Calendar ca = Calendar.getInstance();
		
		// java 1.8 에서 사용하는 클래스
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		/*
		 * Builder Pattern 을 사용하여 IolistVO 객체 생성하기
		 * Builder Pattern 을 사용하면 필요한 필드에 값만 세팅하면서
		 * 객체를 생성할 수 있다
		 */
//		IolistVO vo = new IolistVO();
//		vo.setIo_date(localDateTime.format(dayFormat));
//		vo.setIo_time(localDateTime.format(timeFormat));
//		
		IolistVO vo = IolistVO.builder()
				.io_date(localDateTime.format(dayFormat))
				.io_time(localDateTime.format(timeFormat))
				.build();
		
		model.addAttribute("IO",vo);
		model.addAttribute("BODY","IOLIST_INPUT");
		return "layout";
	}

	/*
	 * POST /insert 와 POST /update/seq 로 요청이 들어오면
	 * 모두 처리하는 method 
	 */
	@RequestMapping(value={"/insert", "/update/{seq}"}, method=RequestMethod.POST)
	// required = name 은 필수아님, 없으면 value = ""
	public String insertOrUpdate(@PathVariable(name = "seq", required = false, value = "")
	String seq,IolistVO iolistVO,Model model) {
		if(seq != null) {
			iolistVO.setIo_seq(Long.valueOf(seq));
		}
		log.debug(iolistVO.toString());
		int result = iolistDao.insertOrUpdate(iolistVO);
		if(result > 0) {
			return "redirect:/iolist/"; 			
		} else {
			model.addAttribute("BODY","IOLIST_INPUT");
			return "layout";
		}
	}
	
	@RequestMapping(value="/detail/{seq}",method=RequestMethod.GET)
	public String detail(@PathVariable("seq")String seq,Model model) {
		Long io_seq = Long.valueOf(seq);
		IolistVO vo = iolistDao.findBySeq(io_seq);
		model.addAttribute("IO",vo);
		model.addAttribute("BODY","IOLIST_DETAIL");
		return "layout";
	}
	
	@RequestMapping(value="/update/{seq}",method=RequestMethod.GET)
	public String update(@PathVariable("seq")String seq, Model model) {
		Long io_seq = Long.valueOf(seq);
		IolistVO vo = iolistDao.findBySeq(io_seq);
		model.addAttribute("IO",vo);
		model.addAttribute("BODY","IOLIST_INPUT");
		return "layout";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.GET)
	public String delete(@PathVariable("seq")String seq, Model model) {
		Long io_seq = Long.valueOf(seq);
		int ret = iolistDao.delete(io_seq);
		return "redirect:/iolist";
	}
	
	@ModelAttribute("SEARCH")
	private SearchDto searchDto() {
		return new SearchDto();
	}
}
