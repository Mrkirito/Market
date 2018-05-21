package com.hangjia.bxj.session.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hangjia.bxj.model.User;

@Component
public class SpringMVCUserSessionManager implements UserSessionManager {
	
	/**
	 * 已登录用户，在 Session 中存储的 key。
	 */
	private static final String SESSION_KEY_LOGINED_USER = "SSO_LOGIN_USER";

	@Override
	public User getCurrentUser() {
		return (User) getUserModel();
	}
	
	@Override
	public User getCurrentUserNotNull() throws SessionTimeoutException {
		User user = getCurrentUser();
		if (user == null) {
			throw new SessionTimeoutException();
		}
		return user;
	}
	
	@Override
	public Integer getCurrentUserId() throws SessionTimeoutException {
		return getCurrentUserNotNull().getId();
	}
	
	@Override
	public void checkLogin() throws SessionTimeoutException {
		if (getUserModel() == null) {
			throw new SessionTimeoutException();
		}
	}
	
	@Override
	public boolean isLogin() {
		return getUserModel() != null;
	}
	
	@Override
	public User login(LoginToken loginToken) {
		User user = null;
		
		if (loginToken instanceof UsernamePasswordLoginToken) {
			// TODO 使用用户名密码查找用户，在这里为 user 赋值，不可为空。
			
			user = findUser((UsernamePasswordLoginToken) loginToken);
			
			if (user == null) {
				throw new LoginException("用户名或密码错误！");
			}
			
		} else {
			// TODO 其他登录方式，比如 jw token 等，可追加 elseif
			// 当登录方式比较多时，可考虑放到一个 hashmap 里。
			
			throw new IllegalStateException("没有合适的登录方法！");
		}
		
		HttpSession session = getRequest().getSession();
		session.setAttribute(SESSION_KEY_LOGINED_USER, user);
		
		return user;
		
	}
	
	private User findUser(UsernamePasswordLoginToken loginToken) {
		User u = new User();
		u.setId(2);
		return u;
	}

	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	private Object getUserModel() {
		HttpSession session = getRequest().getSession(false);
		if (session == null) {
			return null;
		}
		return session.getAttribute(SESSION_KEY_LOGINED_USER);
	}

}
