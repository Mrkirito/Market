package com.hangjia.bxj.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.LoginUserMapper;
import com.hangjia.bxj.model.LoginUser;
@Service
@Transactional(rollbackFor=Throwable.class)
public class LoginService{
	
	@Autowired
	private LoginUserMapper loginUserMapper;
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
    public LoginUser queryUserByCode(LoginUser loginUser){
    	if(null != loginUser && StringUtils.isNotBlank(loginUser.getUserCode())){
    		LoginUser loginResult = loginUserMapper.selectByUserCode(loginUser);
    		if(null != loginResult){
    			return loginResult;
    		}
    	}
    	return null;
    }
}
