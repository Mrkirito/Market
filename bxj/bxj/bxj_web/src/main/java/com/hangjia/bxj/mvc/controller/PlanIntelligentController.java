package com.hangjia.bxj.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.model.PlanIntelligent;
import com.hangjia.bxj.service.intelligent.PlanIntelligentService;

@Controller
@RequestMapping("/intelligent")
public class PlanIntelligentController {
	
	@Autowired
	private PlanIntelligentService service;
	
	@RequestMapping("/index.page")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/intelligent/index");
		modelAndView.addObject("categroyList", service.getPlanProductCategroyList());
		return modelAndView;
	}
		
	@RequestMapping("/intelligent.do")
	@ResponseBody
	public Map<String, Object> intelligent(PlanIntelligent planIntelligent) {
		return service.intelligent(planIntelligent);
	}
}
