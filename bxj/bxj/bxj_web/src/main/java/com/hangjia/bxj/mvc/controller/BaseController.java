package com.hangjia.bxj.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制基类
 */
public class BaseController {

    /**
     * @author Tain
     * @since 2015-10-27
	 * 取得带相同前缀的Request Parameters
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public Map<String, String> getPara(ServletRequest res) {
		HttpServletRequest request = (HttpServletRequest) res;
		Map<String, String> paraMap = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		java.util.Enumeration<String> pns = request.getParameterNames();
		String key = "";
		char a = 6;
		while (pns.hasMoreElements()) {
			key = pns.nextElement();
			String[] vs = request.getParameterValues(key);
			String value = "";
			if (vs.length == 1)
				value = vs[0].trim();
			else {
				for (String v : vs)
					value += v.trim() + a;
				value = value.substring(0, value.length() - 1);
			}
			// value = Utils.htmEncode(value);// //特殊字符转换
			if (!paraMap.containsKey(key)) {
				paraMap.put(key, value);
			} else {
				paraMap.put(key, paraMap.get(key).toString() + a + value);
			}
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
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
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("ip");
		}
		
		paraMap.put("ip",ip);
		return paraMap;
	}


}
