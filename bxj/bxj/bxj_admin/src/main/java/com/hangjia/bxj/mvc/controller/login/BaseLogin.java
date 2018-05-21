package com.hangjia.bxj.mvc.controller.login;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hangjia.bxj.dao.LoginUserMapper;
import com.hangjia.bxj.model.LoginUser;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.common.BaseModule;

/** 
* @author  作者 : yaoy
* @date 2016年5月25日 下午2:30:15 
* @version 1.0 
*/
public class BaseLogin extends BaseModule{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LoginUserMapper loginUserMapper;
	
	/**
	 * 保存登录用户信息到session
	 */
	protected void addSessionParams(HttpSession session, LoginUser loginUser){
		
        session.setAttribute(AdminConstants.LOGIN_USER_ID, loginUser.getId());
        session.setAttribute(AdminConstants.LOGIN_USER, loginUser);
        session.setAttribute(AdminConstants.LOGIN_USER_NAME, loginUser.getUserName());
        session.setAttribute(AdminConstants.IS_LOGIN, "true");
        session.setAttribute(AdminConstants.USER_THEME_NAME, loginUser.getThemeName());
        
        LoginUser update = new LoginUser();
        update.setId(loginUser.getId());
        update.setLastLoginTime(new Date());
        loginUserMapper.updateByPrimaryKeySelective(update);
	}
}
