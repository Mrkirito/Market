package com.znb.cms.exceptions;

/**
 * 继承于Exception的异常处理基类
 *
 * @author 
 * @since 2015-10-27
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -3329308013741495561L;

    protected String code;

    protected String message;

    public BaseException() {
        super();
    }

    public BaseException(String code) {
        super();
        if (null != code) {
            this.code = code;
        }
    }

    public BaseException(ExceptionEnum exceptionEnum) {
        super();
        if (null != exceptionEnum) {
            this.code = exceptionEnum.getCode();
            this.message = exceptionEnum.getMessage();
        }
    }

    public BaseException(String code, String message) {
        super();
        if (null != code) {
            this.code = code;
        }

        if (null != message) {
            this.message = message;
        }
    }

    @Override
    public String toString() {
        return this.getClass() + "(code:" + code + ",message:" + message + ")";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
