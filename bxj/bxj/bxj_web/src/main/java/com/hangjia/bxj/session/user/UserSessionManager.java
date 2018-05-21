package com.hangjia.bxj.session.user;

import com.hangjia.bxj.model.User;

/**
 * 用户 Session 管理器，
 * 此接口提供获取当前用户回话状态。
 * @author K9999
 * @deprecated 此接口停用，改用秦岭的 {@link WebUtils}。
 */
public interface UserSessionManager {

	/**
	 * 返回当前已登录的用户信息。
	 * @return 当前已登录的用户信息，或 {@code null}（用户未登录）。
	 */
	User getCurrentUser();
	
	/**
	 * 返回当前已登录的用户ID。
	 * @return 当前已登录的用户ID。
	 * @throws SessionTimeoutException 如果当前用户没有登录。
	 */
	Integer getCurrentUserId() throws SessionTimeoutException;
	
	/**
	 * 返回当前已登录的用户信息。
	 * @return 当前已登录的用户信息，返回结果不为空。
	 * @throws SessionTimeoutException 如果当前用户没有登录。
	 */
	User getCurrentUserNotNull() throws SessionTimeoutException;
	
	/**
	 * 返回当前用户是否已登录。
	 * @return 如果已登录，返回 {@code true}，否则返回 {@code false}。
	 */
	boolean isLogin();
	
	/**
	 * 断言当前用户已经的登录。
	 * 如果断言成立（已登录），此方法正常结束，否则（未登录）抛出异常。
	 * @throws SessionTimeoutException 如果当前用户没有登录。
	 */
	void checkLogin() throws SessionTimeoutException;
	
	/**
	 * 用户登录。
	 * @param loginToken
	 * @return
	 * @throws LoginException
	 */
	User login(LoginToken loginToken) throws LoginException;
	
}
