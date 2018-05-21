package cn.usst.market.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.po.CustomerSupport;
import cn.usst.market.service.CustomerSupportService;

@Controller
public class CustomerSupportController {
	@Autowired
	private CustomerSupportService customerSupportService;
	
	@RequestMapping("/customerService.do")
	public ModelAndView addCustomerSupport(HttpServletRequest request, CustomerSupport customerSupport){
		ModelAndView modelAndView = new ModelAndView();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		customerSupport.setSubmitDate(sdf.format(date));
		customerSupport.setStatus("待审核");
		int tag = customerSupportService.insertCustomerSupport(customerSupport);
		if(tag==0){
			modelAndView.addObject("message", "提交失败！！");
		}else{
			modelAndView.addObject("message", "提交成功！！");
		}
		modelAndView.setViewName("customerService");
		return modelAndView;
	}
}
