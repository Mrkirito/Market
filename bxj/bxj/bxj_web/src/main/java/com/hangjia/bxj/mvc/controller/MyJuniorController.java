package com.hangjia.bxj.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baobao.sso.filter.AuthenType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.SSOUser;
import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.model.MyJunior;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.junior.MyJuniorsService;

/**
 * 邀请好友加入。
 * @author K9999
 *
 */
@Controller
@RequestMapping("junior")
public class MyJuniorController {

	@Autowired
	private MyJuniorsService myJuniorsService;
	
	/**
	 * 首页视图
	 * @return
	 */
	@RequestMapping(value="index.page", method=RequestMethod.GET)
	@Login(AuthenType.page)
	public Object showIndexView(HttpServletRequest request) {
		
		// 推荐首页不强制登录，可让用户先看看有没奖励。。。
		
//		SSOUser user = WebUtils.getSessionMember(request);
		Integer currentUserId = WebUtils.getMemberId(request);
		if (currentUserId != null&&currentUserId>0) {
			int count = myJuniorsService.showMyJuniorCount(currentUserId);			
			// 用户ID做为推荐人ID
			request.setAttribute("shareId", currentUserId);
			request.setAttribute("count", count);
		} else {
			request.setAttribute("count", 0);
		}
		
		return "junior/index";
	}
	
	/**
	 * 显示“我的晚辈们”视图。
	 * 经由我分享的链接页面注册的用户，都是我的晚辈们。
	 * @param request
	 * @return
	 */
	@Login
	@RequestMapping(value="myJuniors.page", method=RequestMethod.GET)
	public Object showMyJuniorsView(HttpServletRequest request) {
		Integer currentUserId = WebUtils.getMemberId(request);
		List<MyJunior> myJuniors = myJuniorsService.showMyJuniors(currentUserId);
		request.setAttribute("model", myJuniors);
		return "junior/myJuniors";
	}
	
	/**
	 * 分享出去后或扫码进入的页面。
	 * 此页面提示用户输入手机号注册并下载APP。
	 * 
	 * 一般用户是不会进入这个页面的，进入此界面的
	 * 都是未注册用户。
	 * @return
	 */
	@RequestMapping(value="share.page", method=RequestMethod.GET)
	public Object showSharedView(@RequestParam Integer shareId) {
		
		return "junior/shared";
	}
	
	/**
	 * 好友注册。
	 * @param shareId 分享人（邀请人）ID。当前用户则为被邀请人。
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="regist.do", method=RequestMethod.POST)
	public Object regist(HttpServletRequest request, @RequestParam Integer shareId, @RequestParam String mobile) {
		
		// 用户不能推荐自己，其他暂时不做限制
		String phone="";
		try {
			phone=WebUtils.getMobile(request);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isNotEmpty(phone)&&mobile.equals(phone)) {
				throw new BXJException("不能邀请自己");
		}
		
		myJuniorsService.regist(shareId, phone);
		return new AjaxResult.success();
	}
	
}
