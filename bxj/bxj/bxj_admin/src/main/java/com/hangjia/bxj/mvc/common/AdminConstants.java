package com.hangjia.bxj.mvc.common;

/**
 * 后台常量
 * @author yaoy
 *
 */
public class AdminConstants {
	
	public static final String LOGIN_USER_ID = "login_user_id";
	public static final String LOGIN_USER = "login_user";
	public static final String LOGIN_USER_NAME = "_u_n_";
	public static final String IS_LOGIN = "isLogin";
	public static final String USER_THEME_NAME = "_u_t_n_";
	
	/** 首页配置fid 1计划书 2邀请函 3名片 4保险头条 5新人通 6赠送 7保险计算器 8意见反馈 11邀请好友 12冠军论坛 13赠险 **/
	public static final int HOME_FID1 = 1;
	public static final int HOME_FID2 = 2;
	public static final int HOME_FID3 = 3;
	public static final int HOME_FID4 = 4;
	public static final int HOME_FID5 = 5;
	public static final int HOME_FID6 = 6;
	public static final int HOME_FID7 = 7;
	public static final int HOME_FID8 = 8;
	public static final int HOME_FID9 = 9;
	public static final int HOME_FID10 = 10;
	public static final int HOME_FID11 = 11;
	
	/** 音/视频 券id 暂时只有一种券 **/
	public static final Long VOUCHER_ID = 1L;
	
	/** 当前访问路径 **/
	public static final String CURRENT_TARGET = "currentTarget";
	
	public static final String NAV_BAOXIANJIA_CMS = "nav_baoxianjia_cms";
	
	public static final String NAV_XINGAINIAN_CMS = "nav_xingainian_cms";
	
	public static String navCodes;
	
	//public static String[] NAV_CODE = navCodes.split(",") ;
	
	
	public static final String ADMIN_NAME = "superman";
	
	public static String getNavCodes() {
		return navCodes;
	}

	public static void setNavCodes(String navCodes) {
		AdminConstants.navCodes = navCodes;
	}

	public static final String DEFAULT_PASSWD = "bxj_123456";
	
	/** 用户权限 **/
	public static final String USER_FUNCTION = "userFunction";
	
	/** 用户角色 **/
	public static final String USER_ROLE_CODE_LIST = "userRoleCodeList";
}
