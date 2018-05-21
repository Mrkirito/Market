package com.hangjia.bxj.exception;

/**
 * 系统异常枚举类
 * @author Tain
 * @since 2015-10-27
 */
public enum SystemExceptionEnum implements ExceptionEnum {
	
	DB_ERROR_EXCEPTION("S0001", "数据库异常，请重试！"),
	SYS_ERROR_EXCEPTION("S0002", "系统异常，请重试！");
	
	private String code;
	
	private String message;

	private SystemExceptionEnum(String code, String message) {
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
