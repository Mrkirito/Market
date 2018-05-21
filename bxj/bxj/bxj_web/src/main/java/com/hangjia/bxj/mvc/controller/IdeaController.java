package com.hangjia.bxj.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.WebUtils;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.idea.MemberIdeaService;


/**
 * 用户意见反馈
 * @author Coffee
 *
 */
@Controller
@RequestMapping("idea")
public class IdeaController {
	
	@Autowired
	private MemberIdeaService memberIdeaService;

	/**
	 * 版块首页：用户意见反馈
	 * @return
	 */
	@RequestMapping(value="index.page", method=RequestMethod.GET)
	public Object showIndexView(HttpServletRequest request) {
		// 用户手机号，在联系方式输入框中预先提示，也允许用户修改
		String phone="";
		try {
			phone=WebUtils.getMobile(request);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("mobile",phone);
		return "idea/index";
	}
	
	/**
	 * 用户意见提交。
	 * @param text 意见内容。
	 * @param contact 联系方式描述。
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="submit.do", method=RequestMethod.POST)
	public Object submit(HttpServletRequest request, @RequestParam String text, @RequestParam String contact) {
		Integer currentUserId = WebUtils.getMemberId(request);
		memberIdeaService.save(text, contact, currentUserId);
		return new AjaxResult.success("意见已经收到，感谢参与");
	}
	
}
