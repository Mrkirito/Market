package com.hangjia.bxj.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.my.MyVideoVoucherService;

/**
*  @ClassName: MyInviteVoucherController
 * @Description: 我的邀请人数和视频券
 * @author: he-Yi
 * @date: 2016年4月18日 下午8:23:29
 */
@Controller
@RequestMapping("myInvite")
public class MyInviteVoucherController {
	@Autowired
	private MyVideoVoucherService myVideoVoucherService;
    
	/**
	 * 跳转我的邀请
	 * @param request
     * @return
     */
	@RequestMapping(value = "toMyInviteVoucher.page")
	@Login(AuthenType.page)
	public Object toRegist(HttpServletRequest request, String hd) {
		request.setAttribute("videoId", 1); //1 表示视频券 
		Integer userId = null;
		//先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			return new AjaxResult.success("用户id为空！");
		}
		request.setAttribute("userId", userId);
		hd = null==hd?null:"1";
		request.setAttribute("hd", hd);
		return "myinvite/invnetfriend_inapp";
	}
	
	/**
	 * 获取已获得视频券总数
	 *  
	 * @param videoId
	 * @return  
	 */
	@RequestMapping(value = "qryVoucherGetTotal.json")
	@ResponseBody
	public Object queryUserVoucherTotal(HttpServletRequest request, Long videoId) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		//先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			respMap.put("跳转", "登陆失败");
			return new AjaxResult.success(respMap);
		}
		return new AjaxResult.success(myVideoVoucherService.queryUserVoucherTotal(userId.longValue(), videoId));
	}

	/**
	 * 查看邀请人总数
	 * 
	 * @return
	 */
	@RequestMapping(value = "qryInviteTotal.json")
	@ResponseBody
	public Object selectCountInvite(HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		//先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			respMap.put("跳转", "登陆失败");
			return new AjaxResult.success(respMap);
		}
		return new AjaxResult.success(myVideoVoucherService.selectCountInvite(userId.longValue()));
	}
	
	/**
	 * 广告页面 
	 * @param request
	 * @param hd
	 * @return
	 */
	@RequestMapping(value = "sendActivity.page")
	@Login(AuthenType.page)
	public Object sendActivity(HttpServletRequest request, String hd) {
		Integer userId = null;
		//先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			return new AjaxResult.success("用户id为空！");
		}
		/*request.setAttribute("userId", userId);
		hd = null==hd?null:"1";
		request.setAttribute("hd", hd);*/
		return "activity/send_activity";
	}
	 
}
