package com.hangjia.bxj.exception;

/**
 * 计划书功能模块异常枚举数据
 * @author Tain
 * @since 2015-10-27
 */
public enum PlanExceptionEnum implements ExceptionEnum {

	WE_CHAT_EXCEPTION("P0001", "微信信息获取失败！"),
	LOGIN_EXCEPTION("P0002", "用户登陆异常，请重新登陆！"),
	FILE_UPLOAD_EXCEPTION("P0003", "用户头像上传异常！"),
	USER_LOGIN_EXCEPTION("P0004", "用户异常！");
	
	private String code;
	
	private String message;
	
	private PlanExceptionEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String toString() {
		return super.toString() + "("+code+","+message+")";
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
