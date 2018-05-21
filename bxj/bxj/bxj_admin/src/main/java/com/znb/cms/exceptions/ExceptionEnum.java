package com.znb.cms.exceptions;

/**
 * 异常枚举接口类
 *
 * @author 
 * @since 2015-10-27
 */
public interface ExceptionEnum {

    /**
     * 获取异常代码
     *
     * @return 异常代码
     */
    public String getCode();

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    public String getMessage();

}
