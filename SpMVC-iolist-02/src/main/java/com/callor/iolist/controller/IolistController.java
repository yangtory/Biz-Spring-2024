package com.callor.iolist.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public String insert(@ModelAttribute(name="IO") IolistVO iolistVO,
			Model model,HttpSession httpSession) {
		UserVO userVO = (UserVO) httpSession.getAttribute(NamesValue.SESSION.USER);
		if(userVO == null) {
			return "redirect:/user/login?error=needs";
		}
		
		model.addAttribute("IO",iolistVO);
		model.addAttribute("BODY","IOLIST_INPUT");
		return "layout";
	}

	@RequestMapping(value={"/insert", "/update/{seq}"}, method=RequestMethod.POST)
	// required = name 은 필수아님, 없으면 value = ""
	public String insertOrUpdate(@PathVariable(name = "seq", required = false, value = "")
						String seq,@ModelAttribute("IO") IolistVO iolistVO,Model model) {
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
	
	
	// 여기서만 쓸거라서 private 로 만듬
	//ModelAttribute 로 데이터 만들기
	@ModelAttribute("SEARCH")
	private SearchDto searchDto() {
		return new SearchDto();
	}
	
	@ModelAttribute(name = "IO")
	private IolistVO iolistVO() {
		LocalDateTime localDateTime = LocalDateTime.now();
		
		DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		IolistVO vo = IolistVO.builder()
				.io_date(localDateTime.format(dayFormat))
				.io_time(localDateTime.format(timeFormat))
				.build();
		
		return vo;
	}
}
