package com.hangjia.bxj.query.account;

import com.hangjia.bxj.common.BaseCommonQuery;


/**
 * @author yaoy
 * @since 2016-06-17
 */
public class UserQuery extends BaseCommonQuery {

    private Long id;
    /**
     * 员工号，登录使用
     */
    private String userCode;
    /**
     * 用户名称
     */
    private String userName;
    private String userNameLike;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 职称 数据字典
     */
    private String positionTitle;
    /**
     * 最后登录时间
     */
    private String lastLoginTime;
    /**
     * 状态：1 有效，0 无效
     */
    private Integer status;

    private Long roleId;
    private Long notRoleId;

    public Long getNotRoleId() {
        return notRoleId;
    }

    public void setNotRoleId(Long notRoleId) {
        this.notRoleId = notRoleId;
    }

    public String getUserNameLike() {
        return userNameLike;
    }


    public void setUserNameLike(String userNameLike) {
        this.userNameLike = userNameLike;
    }


    public Long getRoleId() {
        return roleId;
    }


    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUserCode() {
        return userCode;
    }


    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getPositionTitle() {
        return positionTitle;
    }


    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }


    public String getLastLoginTime() {
        return lastLoginTime;
    }


    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }
}
