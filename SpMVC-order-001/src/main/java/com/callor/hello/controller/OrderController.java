package com.callor.hello.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.hello.models.OrderCustomVO;
import com.callor.hello.models.OrderVO;
import com.callor.hello.persistance.OrderCustomDao;
import com.callor.hello.persistance.OrderDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/order")
public class OrderController {

	private final OrderCustomDao orderDao;
	public OrderController(OrderCustomDao orderDao) {
		this.orderDao = orderDao;
	}
	
	// "/"가 있거나 "" 비어있거나
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String home(Model model) {
		List<OrderCustomVO>orderList = orderDao.selectAll();
		log.debug(orderList.toString());
		model.addAttribute("ORDER_LIST",orderList);
		return "order/list";
	}
	
	
	
}
