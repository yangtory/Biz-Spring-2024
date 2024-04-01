package com.callor.iolist.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.iolist.models.IolistVO;
import com.callor.iolist.persistance.IolistDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	private static final String IolistVO = null;
	private final IolistDao iolistDao;
	public HomeController(IolistDao iolistDao) {
		this.iolistDao = iolistDao;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<IolistVO>iolist = iolistDao.selectAll();
		model.addAttribute("IOLIST",iolist);
		List<IolistVO>sum = iolistDao.sum();
		model.addAttribute("SUM",sum);
		return "home";
	}
	
	@RequestMapping(value="/detail/{seq}", method=RequestMethod.GET)
	public String detail(@PathVariable("seq") String io_seq,Model model) {
		IolistVO vo = iolistDao.findById(io_seq);
		model.addAttribute("item",vo);
		return "detail";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Locale locale, Model model ) {
		Date date = new Date();
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault() );
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:MM:ss", Locale.getDefault() );
		String formattedDate = dateFormat.format(date);
		String formattedTime= timeFormat.format(date);		
		
		model.addAttribute("serverDate", formattedDate );
		model.addAttribute("serverTime", formattedTime);
		
		return "insert";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(IolistVO vo) {
		iolistDao.insert(vo);
		log.debug("insert{}", vo.toString());
		return "redirect:/";
	}
	
	@RequestMapping(value="/update/{seq}",method=RequestMethod.GET)
	public String update(@PathVariable("seq") String io_seq, Model model) {
		IolistVO vo = iolistDao.findById(io_seq);
		model.addAttribute("item",vo);	
		return "insert";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.POST)
	public String update(@PathVariable("seq") String io_seq,IolistVO vo) {
		vo.setIo_seq(Integer.valueOf(io_seq));
		iolistDao.update(vo);
		log.debug("update{}", vo.toString());
		String retString = String.format("redirect:/detail/%s", io_seq );
		return retString;
	}
	
	@RequestMapping(value="/delete/{seq}",method=RequestMethod.GET)
	public String delete(@PathVariable ("seq") String io_seq) {
		iolistDao.delete(io_seq);
		return "redirect:/";
	}
	
}
