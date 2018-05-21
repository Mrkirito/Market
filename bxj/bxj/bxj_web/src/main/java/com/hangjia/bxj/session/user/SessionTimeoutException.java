package com.hangjia.bxj.session.user;

import com.hangjia.bxj.BXJException;

public class SessionTimeoutException extends BXJException {

	public SessionTimeoutException() {
		super("请登录");
	}
	
	private static final long serialVersionUID = 2003708512327004200L;

}
