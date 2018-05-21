package cn.usst.market.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.po.PysicalEmploeePo;
import cn.usst.market.po.PysicalEmploeeVo;
import cn.usst.market.po.SallSituationVo;
import cn.usst.market.service.PysicalPathService;
import cn.usst.market.service.impl.SearchServiceImpl;

@Controller
public class PysicalPathController {
	
	@Autowired
	private PysicalPathService PPS;
	
	@Autowired
	private SearchServiceImpl SSI;



}
