package com.hangjia.bxj.mvc;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.framework.security.Authorization;
import com.baobao.sso.client.SSOUser;
import com.baobao.sso.client.WebUtils;
import com.baobao.sso.service.UserService;
import com.hangjia.bxj.service.junior.MyJuniorsService;
/**
 * 自动登录拦截器。
 * 所有请求自动登录。
 * @author K9999
 *
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService ssoService;
	
	@Autowired
	private MyJuniorsService myJuniorsService;
	
	private static final Log log = LogFactory.getLog(AuthenticationInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Authorization currentUser = WebUtils.getAuthorization();
		if (currentUser != null) {			
			active(currentUser);
		}
		
		return true;
	}
	
	private  void active(Authorization currentUser) {
		SSOUser u=ssoService.getSSOUserByUid(currentUser.getId()+"");
		Date lastLoginTime = u.getLastLoginDate();
		Date createTime = u.getCreateTime();
		boolean first = lastLoginTime == null || lastLoginTime.equals(createTime);
		
		if (log.isDebugEnabled()) {
			log.debug("判定激活，上次登录：" + lastLoginTime + "，创建时间：" + createTime + "，是否初次：" + first);
		}
		
		if (first) {
			myJuniorsService.active(currentUser.getMobile());
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
	
}
