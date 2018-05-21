package com.hangjia.bxj.mvc.common.account;

/**
 * @author yaoy
 * @since 2016-06-23
 */
public interface AccountForm {
    // 创建帐号失败
    String ADD_ACCOUNT_ERROR = "创建帐号失败";
    // 帐号已存在
    String EXISTS_USERCODE = "帐号已存在";
    // 昵称已存在
    String EXISTS_USERNAME = "昵称已存在";
    // 密码不匹配
    String PASSWORD_NOT_EQUAL = "密码不匹配";
    // 密码不能为空
    String PASSWORD_NULL = "密码不能为空";
}
