package com.hangjia.bxj.mvc.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.mvc.common.AdminConstants;

/** 
 * 登录权限拦截
* @author  作者 : yaoy
* @date 2016年5月11日 下午1:25:18 
* @version 1.0 
*/

public class SecurityInterceptor implements HandlerInterceptor {

    private List<String> excludedUrls;

	@Value("${bxj_path}")
	private String baseUrl;
	
    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
    
	/**
	 * (登录拦截)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        String requestUri = request.getRequestURI();
        // 过滤可信的url
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        // 检查是否登录
        if (session.getAttribute(AdminConstants.LOGIN_USER) == null) {
        	StringBuffer returnUrl = new StringBuffer();
        	String returnURL = request.getRequestURL().toString();
        	String queryString = request.getQueryString();
        	if(StringUtils.isNotBlank(queryString)){
        		returnUrl.append(returnURL).append("?").append(queryString);
        	} else {
        		returnUrl.append(returnURL);
//        		returnUrl.append(StringUtils.substring(StringUtils.split(returnURL, ".")[0], 1));
        	}
        	response.sendRedirect(baseUrl + "/login.jhtml?returnUrl=" + returnUrl);
        	return false;
        } else {
            return true;
        }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
