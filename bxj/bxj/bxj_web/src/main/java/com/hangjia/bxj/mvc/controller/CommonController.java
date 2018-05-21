package com.hangjia.bxj.mvc.controller;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.baobao.sso.filter.Login;
@Controller
@RequestMapping(value="/common")
public class CommonController {	
	@RequestMapping(value = "{key}.page")
	public ModelAndView common(@PathVariable String key) {
		return new ModelAndView("common/" + key);
	}

	@RequestMapping(value = "redirect.page")
	public ModelAndView redirect(String ret) {
		if (StringUtils.isNotEmpty(ret)) {
			return new ModelAndView(new RedirectView(ret));
		} else {
			return new ModelAndView("error");
		}
	}

	@Login
	@RequestMapping(value = "l_redirect.page")
	public ModelAndView loginRedirect(String ret) {
		if (StringUtils.isNotEmpty(ret)) {
			return new ModelAndView(new RedirectView(ret));
		} else {
			return new ModelAndView("error");
		}
	}
}
