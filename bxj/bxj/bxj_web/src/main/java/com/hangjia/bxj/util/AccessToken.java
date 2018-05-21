package com.hangjia.bxj.util;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AccessToken {

	private static Logger logger = LoggerFactory.getLogger(AccessToken.class);
	
	
	private String tokeString;
	private Long validDate;
	
	public static AccessToken accessToken = null;
	
	public static AccessToken getToken() {
		
		if(accessToken == null || accessToken.getValidDate() < System.currentTimeMillis()) {
			accessToken = getNewOne();
		} 
		
		return accessToken;
	}
	
	public static AccessToken getNewOne(){
		JSONObject json = HjbwxUtil.getTokenStrFromWeiXin();
		logger.info("-----------json: " + json);
		if(json.containsKey("access_token")) {
			accessToken = new AccessToken();
			accessToken.setTokeString(json.getString("access_token"));
			accessToken.setValidDate(System.currentTimeMillis() + (json.getLong("expires_in") -200)*1000);
		} else {
			logger.info("********请求微信接口失败，未能获取到token： " + json);
		}
		
		return accessToken;
	}
	
	/**
	 * TODO
	 * @param args
	 * @author Liu zhilai
	 * Feb 13, 2015
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getTokeString() {
		return tokeString;
	}

	public void setTokeString(String tokeString) {
		this.tokeString = tokeString;
	}

	public Long getValidDate() {
		return validDate;
	}

	public void setValidDate(Long validDate) {
		this.validDate = validDate;
	}


}
