package com.hangjia.bxj.session.user;

import com.hangjia.bxj.BXJException;

/**
 * 登录异常，
 * 产生的原因可能是用户名密码错误、授权令牌无效或过期等。
 * @author K9999
 *
 */
public class LoginException extends BXJException {

	public LoginException(String message) {
		super(message);
	}
	
	private static final long serialVersionUID = 8763374424691010482L;

}
