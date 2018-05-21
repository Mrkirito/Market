package com.hangjia.bxj.mvc.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestDebugInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log log = LogFactory.getLog(RequestDebugInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (log.isDebugEnabled()) {
			@SuppressWarnings("unchecked")
			Enumeration<String> it = request.getParameterNames();
			
			while (it.hasMoreElements()) {
				String name = it.nextElement();
				String value = request.getParameter(name);
				
				log.debug("请求参数：" + name + "=" + value);
			}
		}
		
		return true;
	}
	
}
