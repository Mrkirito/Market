package com.hangjia.bxj.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.invitation.InvitationService;

/**
 * 邀请函 控制器。
 * @author K9999
 *
 */
@Controller
@RequestMapping("invitation")
public class InvitationController {
	
	@Autowired
	private InvitationService invitationService;
	
	private static final Log log = LogFactory.getLog(InvitationController.class);
	
	@RequestMapping(value="view.page", method=RequestMethod.GET)
	public Object view(@RequestParam Integer shareId) {
		
		return "invitation/view";
	}
	
	/**
	 * 邀请函 首页。
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="index.page", method=RequestMethod.GET)
	public Object index(HttpServletRequest request) {
		Integer userId = WebUtils.getMemberId(request);
		Object model = invitationService.listSimple(userId);
		request.setAttribute("list", model);
		return "invitation/index";
	}
	
	/**
	 * 邀请函 功能页，
	 * 此页面中只包含html片段，不可单独使用。需要在完整的页面中引入。
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="include.page", method=RequestMethod.GET)
	public Object include() {
		return "invitation/include";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="save.do", method=RequestMethod.POST)
	public Object save(HttpServletRequest request, Invitation model) {
		
		if (log.isInfoEnabled()) {
			log.info("保存邀请函：" + model);
		}
		
		Integer userId = WebUtils.getMemberId(request);
		model.setUserId(userId);
		
		invitationService.save(model);
		
		return new AjaxResult.success(model.getId());
	}
	
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public Object list(HttpServletRequest request) {
		Integer userId = WebUtils.getMemberId(request);
		Object result = invitationService.list(userId);
		return new AjaxResult.success(result);
	}
	
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="delete.do", method=RequestMethod.POST)
	public Object delete(HttpServletRequest request, @RequestParam Long id) {
		Integer userId = WebUtils.getMemberId(request);
		invitationService.delete(id, userId);
		return new AjaxResult.success();
	}
	
	@Login(AuthenType.page)
	@RequestMapping(value="update.page", method=RequestMethod.GET)
	public Object update(HttpServletRequest request, @RequestParam Long id) {
		Integer userId = WebUtils.getMemberId(request);
		Object model = invitationService.getUserInvitationNotNull(id, userId);
		request.setAttribute("model", model);
		return "invitation/update";
	}
	
	@Login(AuthenType.page)
	@RequestMapping(value="details.page", method=RequestMethod.GET)
	public Object details(HttpServletRequest request, @RequestParam Long id) {
		Integer userId = WebUtils.getMemberId(request);
		Object model = invitationService.details(id, userId);
		request.setAttribute("model", model);
		return "invitation/details";
	}
	
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="update.do", method=RequestMethod.POST)
	public Object update(HttpServletRequest request, Invitation model) {
		Integer userId = WebUtils.getMemberId(request);
		model.setUserId(userId);
		invitationService.update(model);
		return new AjaxResult.success();
	}
	
	/**
	 * 显示新建邀请函视图。
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="create.page", method=RequestMethod.GET)
	public Object showCreatePage() {
		return "invitation/create";
	}
	
}
