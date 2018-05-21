package com.hangjia.bxj.mvc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hangjia.bxj.model.LoginUser;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.vo.account.UserDTO;

/**
 * <strong>获取web信息</strong> 工具类。
 * <p>
 * 获取web信息
 * 
 * </p>
 * 
 * @author yaoy
 * @since
 */
public class AdminWebUtil {

	public static LoginUser getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return getLoginUser(session);
	}

	public static LoginUser getLoginUser(HttpSession session) {
		Object res = session.getAttribute(AdminConstants.LOGIN_USER);
		if (null != res && res.getClass() == LoginUser.class) {
			return (LoginUser) res;
		}
		return null;
	}

	public static Long getLoginUserId(HttpServletRequest request) {
		LoginUser loginUser = getLoginUser(request);
		if (null != loginUser) {
			return loginUser.getId().longValue();
		}
		return null;
	}

	public static Long getLoginUserId(HttpSession session) {
		LoginUser loginUser = getLoginUser(session);
		if (null != loginUser) {
			return loginUser.getId().longValue();
		}
		return null;
	}

	public static String getLoginUserCode(HttpServletRequest request) {
		LoginUser loginUser = getLoginUser(request);
		if (null != loginUser) {
			return loginUser.getUserCode();
		}
		return null;
	}

	public static String getLoginUserName(HttpServletRequest request) {
		LoginUser loginUser = getLoginUser(request);
		if (null != loginUser) {
			return loginUser.getUserName();
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
