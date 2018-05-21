package cn.usst.market.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.po.Company;
import cn.usst.market.po.OnlineEmploee;
import cn.usst.market.service.OnlineEmploeeService;

@Controller
public class OnlineEmploeeController {
	
	@Autowired
	private OnlineEmploeeService OES;
	
	@RequestMapping("/OnlineEmploeeStart.do")
	private ModelAndView OnlineEmploeeStart(HttpServletRequest request) throws Exception{

		//获取公司ID
		int company_id=(int)request.getSession().getAttribute("companyId");
		//int quater = (int) request.getAttribute("quater");
		int quater = 1;
		
		Company c = new Company();
		c.setId(company_id);
		System.out.println("OES starting");
		OnlineEmploee OE = OES.selectOnlineEmploeebyCompanyID(c);
		System.out.println("OES ending");
		

		//输出查询结果
		int add = 0;//若这个变量被置1，则说明接下来需要创建新表而非更新旧表
		if(quater != OE.getQuater())
			add = 1;
		ModelAndView modelAndView = new ModelAndView();
		int len = 1;
		modelAndView.addObject("salesSalaryList",OE);
		modelAndView.addObject("len",len);
		modelAndView.addObject("quater",quater);
		modelAndView.addObject("add",add);
		modelAndView.setViewName("onlineemploeehiring");
		
		return modelAndView;
		
	}
	
	@RequestMapping("/OnlineEmploeeSingin.do")
	private ModelAndView OnlineEmploeeSingin(HttpServletRequest request) throws Exception{
		System.out.println("Called");
		//获取公司ID
		int company_id=(int)request.getSession().getAttribute("companyId");
		String quaterS[] = request.getParameterValues("quater");
		String addS[] = request.getParameterValues("add");
		System.out.println("quaterS : "+quaterS);
		int quater = Integer.parseInt(quaterS[0]);
		int add = Integer.parseInt(addS[0]);
		System.out.println("company_id : "+company_id);
		System.out.println("quater : "+quater);
		
		//buffer初始化
		OnlineEmploee oe = new OnlineEmploee();
		
		//获取各项基本数据
		String saller[] = request.getParameterValues("ecnomical");
		String aftersale[] = request.getParameterValues("aftersale");
				
		oe.setAftersale(Integer.parseInt(aftersale[0]));
		oe.setCompanyid(company_id);
		oe.setQuater(quater);
		oe.setSaler(Integer.parseInt(saller[0]));
		
		if(add == 0)
			OES.updateOnlineEmploeebyCompanyID(oe);
		else
			OES.insertCompanyEmploeeInfo(oe);
				
		return OnlineEmploeeStart(request);
	}
}
