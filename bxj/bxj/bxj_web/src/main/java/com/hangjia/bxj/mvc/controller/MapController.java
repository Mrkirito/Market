package com.hangjia.bxj.mvc.controller;

import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("map")
public class MapController {

	@RequestMapping(value="navigation.page", method=RequestMethod.GET)
	public Object navigation(HttpServletRequest request) {
		request.setAttribute("titleName", request.getParameter("titleName"));
		return "map/navigation";
	}

	@RequestMapping(value="close.page", method=RequestMethod.GET)
	@Login(AuthenType.page)
	public Object close() {
		return "map/close";
	}
	
}
