package cn.usst.market.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.po.GlobalPathNeedsVo;
import cn.usst.market.po.SaleIncomVo;
import cn.usst.market.po.SallSituationVo;
import cn.usst.market.po.StoreInforVo;
import cn.usst.market.service.PysicalPathService;
import cn.usst.market.service.SearchService;

@Controller
public class InforController {

	@Autowired
	private SearchService SS;
	
	@Autowired
	private PysicalPathService PPS;
	
	/**
	 * 各产品销量统计调用这个controller
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/SalePathInfor.do")
	private ModelAndView SalePathInfor(HttpServletRequest request) throws Exception{
		System.out.println("SalePathInfor.do called");
		//获取公司ID
		int company_id=(int)request.getSession().getAttribute("companyId");
		int quarter = Integer.parseInt(request.getParameter("quarter"))-1;
		
		System.out.println("company_id ："+company_id);
		System.out.println("quarter ："+quarter);
		System.out.println("DataBaseCalled");
		List<SallSituationVo> ListSSV = SS.selectSaleSituationbyCompanyID(company_id,quarter);
		
		/*System.out.println("ListSSV");
		System.out.println("ListSSV.get(0).getSaleIncom() : "+ListSSV.get(0).getSaleIncom());
		System.out.println("ListSSV.get(0).getPMS().getNeed() : "+ListSSV.get(0).getPMS().getNeed());*/
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listssv",ListSSV);
		modelAndView.setViewName("SalePath");
		System.out.println("Web page name : SalePath.jsp");
		return modelAndView;
	}
	
	/**
	 * 渠道盈利能力调用此controller
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/SaleAbilityInfor.do")
	private ModelAndView SaleAbilityInfor(HttpServletRequest request) throws Exception{
		System.out.println("InforController:SaleAbilityInfor.do called");
		//获取公司ID
		int company_id=(int)request.getSession().getAttribute("companyId");
		int quarter = Integer.parseInt(request.getParameter("quarter"))-1;
		System.out.println("company_id: "+company_id);
		System.out.println("quarter: "+quarter);
		List<SaleIncomVo> ListSSV = SS.selectPathAbilitybyCompanyID(company_id, quarter);
		SaleIncomVo vo = new SaleIncomVo();
		for(SaleIncomVo v:ListSSV){
			vo.setSaleIncomSum(vo.getSaleIncomSum()+v.getSaleIncomSum());
			vo.setYoujiSum(vo.getYoujiSum()+v.getYoujiSum());
			vo.setSaleCostSum(vo.getSaleCostSum()+v.getSaleCostSum());
		}
		
		Integer salary_sum = PPS.salary(company_id, quarter);
		
		int fuzzyincom = vo.getSaleIncomSum()-vo.getSaleCostSum();
		int income = fuzzyincom - salary_sum;
		int sa = vo.getSaleIncomSum();
		if(sa == 0)
			sa = 1;//防止分母为0
		double rate = income*100/sa;
		System.out.println("income : "+income);
		System.out.println("salary_sum : "+salary_sum);
		System.out.println("vo.getSaleIncomSum() : "+vo.getSaleIncomSum());
		System.out.println("rate : "+rate);
		if(rate < 0)
			rate = 0;//防止由于利润为0而出现不合理的值
		String ra = rate+"%";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("vo",vo);
		modelAndView.addObject("fuzzyincom",fuzzyincom);
		modelAndView.addObject("income",income);
		modelAndView.addObject("rate",ra);
		modelAndView.addObject("salary_sum",salary_sum);
		modelAndView.setViewName("SaleAbility");
		return modelAndView;
				
	}

	/**
	 * 各渠道需求量调用此controller
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GlobalPathNeeds.do")
	private ModelAndView GlobalPathNeeds(HttpServletRequest request) throws Exception{
		System.out.println("GlobalPathNeeds.do called");
		//获取公司ID
		int company_id=(int)request.getSession().getAttribute("companyId");
		int quarter = Integer.parseInt(request.getParameter("quarter"))-1;
		
		
		List<GlobalPathNeedsVo> ListGPNV = SS.selectGlobalPathSharebycompanyid(company_id,quarter);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ListGPNV",ListGPNV);
		modelAndView.setViewName("GlobalPath");
		return modelAndView;
				
	}
	
	/**
	 * 各个参赛队开设分店情况
	 */
	@RequestMapping("/StoreInfor.do")
	private ModelAndView StoreInfor(HttpServletRequest request) throws Exception{
		System.out.println("StoreInfor.do called");
		//获取公司ID
		int company_id=(int)request.getSession().getAttribute("companyId");
		int quarter = Integer.parseInt(request.getParameter("quarter"))-1;		
		List<StoreInforVo> ListSIV = SS.selectStoreInforbycompanyid(company_id,quarter);
		
		/**
		 * 1~4分别为印俄中新
		 */
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ListSIV",ListSIV);
		modelAndView.setViewName("StoreInfor");
		return modelAndView;
				
	}

}
