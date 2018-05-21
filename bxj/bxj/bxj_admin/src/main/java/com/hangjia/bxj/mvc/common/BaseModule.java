package com.hangjia.bxj.mvc.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.LoginUser;

/**
 * @author yaoy
 * @since 2016-06-14
 */
@Controller
public class BaseModule {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HttpSession session;
	private HttpServletResponse response;

	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	protected boolean checkLogin() {
		Boolean isLogin = (Boolean) session.getAttribute(AdminConstants.IS_LOGIN);
		return isLogin.booleanValue();
	}

	protected LoginUser getLoginUser() {
		return (LoginUser) session.getAttribute(AdminConstants.LOGIN_USER);
	}

	protected Integer getLoginUserId() {
		LoginUser user = getLoginUser();
		if (null != user) {
			return user.getId();
		}
		return null;
	}

	protected String getLoginUserCode() {
		LoginUser user = getLoginUser();
		if (null != user) {
			return user.getUserCode();
		}
		return null;
	}

	protected String getLoginUserName() {
		LoginUser user = getLoginUser();
		if (null != user) {
			return user.getUserName();
		}
		return null;
	}


	protected void AjaxJson(String jsonString) {
		try {
			PrintWriter out = response.getWriter();
			out.print(jsonString);
		} catch (Exception e) {
		}
	}

	protected void AjaxJson(Result result) {
		JSONObject json = (JSONObject) JSONObject.toJSON(result);
		AjaxJson(json.toJSONString());
	}

	protected Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
}
