package com.hangjia.bxj.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.service.BookService;


/**
 * Created by Tain on 2016/3/1.
 */
@Controller
@RequestMapping(value="/plan")
public class BookController {
	@Autowired
	private BookService service;
    @RequestMapping("product")
    public String index() {
        return "index";
    }
    
	@RequestMapping("/banner.jhtml")
	public ModelAndView banner() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("banners", service.getBanner());
		return new ModelAndView("planbook/banner",map);
	}
	
	@RequestMapping("/icon.jhtml")
	public ModelAndView icon() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("icons", service.getIcon());
		return new ModelAndView("planbook/icon",map);
	}
}
