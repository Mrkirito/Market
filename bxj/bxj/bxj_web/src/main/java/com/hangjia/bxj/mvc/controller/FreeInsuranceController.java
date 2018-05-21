package com.hangjia.bxj.mvc.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.model.FreeInsurance;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.free.FreeInsuranceService;
import com.hangjia.bxj.service.free.ReceiverArgs;


/**
 * 免费险。
 * @author K9999
 *
 */
@Controller
@RequestMapping("free")
public class FreeInsuranceController {
	
	@Autowired
	private FreeInsuranceService freeInsuranceService;
	
	/**
	 * 所有免费险产品列表页面。
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping("listFreeInsurances.page")
	public Object listAllFreeInsurances(HttpServletRequest request) {
		Collection<FreeInsurance> list = freeInsuranceService.listAllFreeInsurances();
		request.setAttribute("list", list);
		
		Integer currentUserId = WebUtils.getMemberId(request);
		request.setAttribute("uid", currentUserId);
		return "free/list";
	}
	
	/**
	 * 免费险详情界面，由列表页点击进入。
	 * 这个页面是给客户用的，不需要登录，shareId来源于参数。
	 * @param id
	 * @return
	 */
	@RequestMapping("getFreeInsuranceDetails.page")
	public Object getFreeInsuranceDetails(HttpServletRequest request, @RequestParam String pid, @RequestParam Integer shareId) {
		// 搜索产品，确保产品存在，且可用。
		FreeInsurance model = freeInsuranceService.getFreeInsuranceDetails(pid);
		request.setAttribute("model", model);
		
		// 自 3.0.1 修改，从request中获取
		request.setAttribute("shareId", shareId);
		
		/* 3.0.1 结束 */
		
		return "free/" + pid;
	}
	
	/**
	 * 免费险详情界面，由列表页点击进入。
	 * 唯一的区别是需要登录，且 shareId 是当前用户的ID。
	 * @param request
	 * @param pid
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping("getFreeInsuranceDetails1.page")
	public Object getFreeInsuranceDetails(HttpServletRequest request, @RequestParam String pid) {
		
		Integer userId = WebUtils.getMemberId(request);
		
		FreeInsurance model = freeInsuranceService.getFreeInsuranceDetails(pid);
		request.setAttribute("model", model);
		
		// 自 3.0.1 修改，从request中获取
		request.setAttribute("shareId", userId);
		
		return "free/" + pid;
		
	}
	
	/**
	 * 领取免费险。
	 * @param shareId 分享人ID，也就是用户ID。记录这个是谁分享出去的。
	 * @return
	 */
	@RequestMapping("receive.do")
	@ResponseBody
	public Object receive(@RequestParam Integer shareId,String hd, ReceiverArgs args) {
		long rid = freeInsuranceService.receive(shareId, args);
		return new AjaxResult.redirct("free/freesucc.page?receiveId=" + rid+(StringUtils.isNotBlank(hd)?"&hd=1":""));
	}
	
	@RequestMapping("freesucc.page")
	public Object freeSuccess(@RequestParam Long receiveId,String hd) {
		return "free/succ";
	}

}
