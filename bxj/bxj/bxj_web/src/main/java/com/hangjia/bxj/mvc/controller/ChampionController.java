package com.hangjia.bxj.mvc.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.champion.ChampionService;
/**
 * 冠军论坛 功能控制器。
 * @author K9999
 *
 */
@Controller
@RequestMapping("champion")
public class ChampionController {
	
	@Autowired
	private ChampionService championService;
	
	/**
	 * 冠军论坛入口首页。
	 * 冠军论坛是保保的功能，这里的首页只做个入口，输入提取码，成功后跳转到保保页面。
	 * @return
	 */
	@Login
	@RequestMapping(value="index.page", method=RequestMethod.GET)
	public Object showIndexView(HttpServletRequest request) {
		// 增加一个参数判定，携带参数的要提取码，，否则不要。
		if (request.getParameter("code") == "1") {
			// 到首页要求输入提取码
			return "champion/index";
		}
		// 否则这个页面直接跳转
		return "champion/forward";
	}
	
	/**
	 * 验证提取码，
	 * 成功后返回一个跳转链接查看课件。（其实这个链接是公开的（保保提供），只要知道跳转地址，就可以跳过这步直接访问地址）
	 * @param pwd 提取码
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@Login(AuthenType.json)
	@RequestMapping(value="championforum.do", method=RequestMethod.POST)
	@ResponseBody
	public Object championforum(HttpServletRequest request, @RequestParam String pwd) throws UnsupportedEncodingException {
		String url = championService.getLocationByCode(pwd);
		
		StringBuilder sb = new StringBuilder(url);
		if (url.indexOf("?") == 0) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		sb.append("returnurl=");
		
		StringBuilder returnURLSb = new StringBuilder(request.getScheme())
			.append("://")
			.append(request.getServerName())
			.append(":")
			.append(request.getServerPort())
			.append(request.getContextPath());
		
		String returnURL = URLEncoder.encode(returnURLSb.toString(), "UTF-8");
		sb.append(returnURL);
		
		return new AjaxResult.redirct(sb.toString());
	}

}
