package com.hangjia.bxj.mvc.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.model.CustomersPlanBook;
import com.hangjia.bxj.model.InvitationAppointment;
import com.hangjia.bxj.model.PlanBook;
import com.hangjia.bxj.model.PlanBookFeedbacks;
import com.hangjia.bxj.model.PlanDecision;
import com.hangjia.bxj.model.PlanGroupProInfos;
import com.hangjia.bxj.model.PlanProductFl;
import com.hangjia.bxj.service.PlanBookService;
import com.hangjia.bxj.vo.QueryProductVo;

@Controller
@RequestMapping(value="/plan")
public class PlanBookController {
	private static Logger log = LoggerFactory.getLogger(PlanBookController.class);
	@Autowired
	private PlanBookService service;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index.page")
	public ModelAndView index() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("companys", service.getProductGs());
		map.put("banners", service.getBanners());
		map.put("icons", service.getIcons()); 
		return new ModelAndView("/plan/index",map);
	}
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping("/list.page")
	public ModelAndView list(String channel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companys", service.getProductGs());
		map.put("channel", channel);
		map.put("noChannel", StringUtils.isEmpty(channel));
		return new ModelAndView("/plan/list", map);
	}
	/**
	 * 编辑页
	 * @param pid
	 * @param request
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping("/make{pid}.page")
	public ModelAndView make(@PathVariable Integer pid,HttpServletRequest request,HttpServletResponse response) {
		Integer userId = 0;
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			log.info("get user fail...", e);
		}
		//20161125新接口
		String mark = request.getParameter("mark");
		if (StringUtils.isNotBlank(mark)) {
			Map<String, Object> map = service.newMake(pid, userId);
			map.put("mark", mark);
			return new ModelAndView(map.get("target").toString(), map);
		}else{
			//20161125老接口
			Map<String, Object> map = service.make(pid, userId, StringUtils.isNotEmpty(request.getParameter("hasad")));
			Boolean boo= (Boolean) map.get("goAd");
			if(boo){
				String hd=request.getParameter("hd");
				String articleId=request.getParameter("articleId");
				return new ModelAndView("redirect:detail/ad/"+pid+".page?shareios=1&hd="+(StringUtils.isNotEmpty(hd)?hd:"")+"&articleId="+(StringUtils.isNotEmpty(articleId)?articleId:""));
			}else{
				return new ModelAndView(map.get("target").toString(), map);			
			}
		}
	}
	/**
	 * 无登陆版编辑页面
	 * @param pid
	 * @param request
	 * @return
	 */
	@RequestMapping("/noLoginMake{pid}.page")
	public ModelAndView noLoginMake(@PathVariable Integer pid,HttpServletRequest request) {
		Integer userId=Integer.parseInt(request.getParameter("channel"));
		String mark = request.getParameter("mark");
		if (StringUtils.isNotBlank(mark)) {
			Map<String, Object> map = service.newMake(pid, userId);
			map.put("mark", mark);
			return new ModelAndView(map.get("target").toString(), map);
		}else{
			Map<String, Object> map = service.make(pid, userId,StringUtils.isNotEmpty(request.getParameter("hasad")));
			return new ModelAndView(map.get("target").toString(), map);
		}
	}
	/**
	 * 详情页
	 * @param planId
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail{planId}.page")
	public ModelAndView finalPage(@PathVariable Long planId,HttpServletRequest request) {
		Map<String, Object> map= service.finalPage(planId,request.getParameter("customerId"));
		if(map.isEmpty()){
			return new ModelAndView("/plan/error");
		}else{
			/*智云保的上线需求，在第一版中，保险产品的介绍需要借用你们保险家的计划书模块。 所以我请求你们帮忙添加一些新的计划书，以及对计划书的展现做一些调整。非常感谢
			1. 当链接中有"&intell=1"的时候，下面的两个页面不显示出来。 例如：http://sit.hangjiabao.com/bxj_web/plan/detail13378.page?1=1&shareios=1&intell=1&customerId=
			*/			
			String intell=request.getParameter("intell");
			map.put("intell", StringUtils.isNotBlank(intell)&&intell.equals("1"));
			
			map.put("hideHeader", StringUtils.isNotEmpty(request.getParameter("share")));
			return new ModelAndView(map.get("target").toString(),map);			
		}
	}
	/**
	 * 广告页
	 * @param planId
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail/ad/{pid}.page")
	public ModelAndView addetail(@PathVariable Integer pid,HttpServletRequest request) {
		Integer userId = 0;
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			log.info("get user fail...", e);
		}
		String mark = request.getParameter("mark");
		if (StringUtils.isNotBlank(mark)) {
			Map<String, Object> map = service.newMake(pid, userId);
			map.put("mark", mark);
			return new ModelAndView("/product_ad/"+pid, map);
		}else{			
			Map<String, Object> map = service.make(pid, userId, StringUtils.isNotEmpty(request.getParameter("hasad")));
			return new ModelAndView(map.get("target").toString(),map);
		}
	}	
	/**
	 * 查询产品
	 * @param vo
	 * @return
	 */
	@RequestMapping("/query.do")
	@ResponseBody
	public Map<String, Object> query(QueryProductVo vo) {
		return service.query(vo);
	}
	
	/**
	 * 生成计划书
	 * @param planBook
	 * @return
	 */
	@RequestMapping("/bulid.do")
	@ResponseBody
	public Map<String, Object> bulid(PlanBook planBook) {
		return service.bulid(planBook);
	}
	
	/**
	 * 客户列表
	 * @param planBook
	 * @return
	 */
	@RequestMapping("/customerList.do")
	@ResponseBody
	public Map<String, Object> listCustomers(PlanBook planBook) {
		return service.listCustomers(planBook);
	}
	
	/**
	 * 费率查询
	 * @param planBook
	 * @return
	 */
	@RequestMapping("/rate.do")
	@ResponseBody
	public Map<String, Object> rate(PlanProductFl rate) {
		return service.rate(rate);
	}
	
	/**
	 * 存储客户意见
	 * @param planDecision
	 * @return
	 */
	@RequestMapping("/savedecision.do")
	@ResponseBody
	protected Map<String, Object> savedecision(PlanDecision planDecision) {
		return service.savedecision(planDecision);
	}
	
	/**
	 * 查询单个产品
	 * @param fid
	 * @return
	 */
//	@RequestMapping("/getProductByFid.do")
//	@ResponseBody
//	public PlanProductMain getProductByFid(Integer fid){
//		return service.getConfirmPlanProductMain(fid);
//	}
	
	/**
	 * 点赞
	 * @param customersPlanBook
	 * @return
	 */
	@RequestMapping("/agree.do")
	@ResponseBody
	public Map<String, Object> agree(CustomersPlanBook customersPlanBook){
		return service.agree(customersPlanBook);
	}
		
	@RequestMapping("/more.page")
	public ModelAndView more() {
		return new ModelAndView("/plan/more");
	}
    
	/**
	 * PDF条款
	 * @param tk
	 * @return
	 */
	@RequestMapping("/pdf.page")
    private ModelAndView pdf(String tk) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("tk", tk);
        return new ModelAndView("/plan/pdf",map);
    }
    
	/**
	 * 存储反馈意见
	 * @param invitationAppointment
	 * @return
	 */
	@RequestMapping("/saveInvitationAppointment.do")
	@ResponseBody
    @Login(AuthenType.json)
    public Map<String,Object> saveInvitationAppointment(InvitationAppointment invitationAppointment) {
		return service.saveInvitationAppointment(invitationAppointment);
    }
	@RequestMapping("/product_info.page")
    private ModelAndView productdDetailInfo(Integer pid) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("product", service.getPlanProductMainByPid(pid));
        return new ModelAndView("/plan/product_info",map);
    }
	@RequestMapping("/savePlanBookFeedbacks.do")
	@ResponseBody
	public Map<String,Object> savePlanBookFeedbacks(PlanBookFeedbacks feedbacks) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("result", service.savePlanBookFeedbacks(feedbacks)>0);
		return map;
    }
	@RequestMapping("/savePlanGroupProInfos.do")
	@ResponseBody
    @Login(AuthenType.json)
    public Map<String,Object> savePlanGroupProInfos(PlanGroupProInfos vo) {
		return service.savePlanGroupProInfos(vo);
    }
}
