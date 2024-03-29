package com.callor.hello.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.hello.models.ProductVO;
import com.callor.hello.persistance.ProductDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/product")
public class ProductController {
	
	private final ProductDao productDao;
	
	public ProductController(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String home(Model model) {
		List<ProductVO>productList = productDao.selectAll();
		model.addAttribute("PRODUCT_LIST",productList);
		return "product/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		return "product/input";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(ProductVO vo) {
		productDao.insert(vo);
		return "redirect:/product";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(@RequestParam("p_code") String pCode, Model model, 
			@RequestParam(name="msg", required = false, defaultValue = "ok") String msg ) {
		ProductVO product = productDao.findById(pCode);
		model.addAttribute("PRODUCT",product);
		model.addAttribute("MSG",msg);
		return "product/detail";
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@RequestParam("pcode") String pCode, Model model) {
		ProductVO product = productDao.findById(pCode);
		model.addAttribute("PRODUCT", product);
		return "product/input";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(ProductVO productVO) {
		log.debug("Update{}",productVO.toString());
		productDao.update(productVO);
		return "redirect:/product/detail?p_code="+productVO.getP_code();
	}
	
	@RequestMapping(value="/delete/{pcode}", method=RequestMethod.GET)
	public String delete(@PathVariable("pcode")String pCode) {
		int result = 0;
		try {
			result = productDao.delete(pCode);			
		} catch (Exception e) {
			return "redirect:/product/detail?p_code="+ pCode + "&msg=FK";
		}
		if(result>0) {
			return "redirect:/product";			
		}else {
			return "redirect:/product/detail?p_code="+ pCode + "&msg=NOT";
		}	
	}

}
