package com.hangjia.bxj.query.account;

import com.hangjia.bxj.common.BaseCommonQuery;


/**
 * @author yaoy
 * @since 2016-06-17
 */
public class RoleQuery extends BaseCommonQuery {
    private Integer status;
    private Long parentId;
    private String roleCode;
    private String roleName;
    private Long roleId;
    private String[] roleIdList;

    public String[] getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(String[] roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Long getRoleId() {
        return roleId;
    }


    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public Long getParentId() {
        return parentId;
    }


    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public String getRoleCode() {
        return roleCode;
    }


    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
