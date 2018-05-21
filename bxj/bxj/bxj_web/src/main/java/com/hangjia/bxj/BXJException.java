package com.hangjia.bxj;

/**
 * 【保险家】应用异常，
 * 此异常是所以、已主动检测，或已遇见到的（即：已知异常）的根异常。
 * 
 * <p>
 * 所有在此应用中主动抛出的异常，必须继承此异常。
 * 并提示用户出错原因，及如何避免此类情况发生。
 * </p>
 * @author K9999
 * 
 */
public class BXJException extends RuntimeException {

	public BXJException(String message, Throwable cause) {
		super(message, cause);
	}

	public BXJException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 7234424488604611975L;

}
