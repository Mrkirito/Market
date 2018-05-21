package com.hangjia.bxj.model.account;

import com.hangjia.bxj.common.BaseCommonDO;

/**
 * @author yaoy
 * @since 2016-06-20
 */
public class RoleDO extends BaseCommonDO {
    private Long id;
    private String roleCode;
    private String roleName;
    private String memo;
    private Long parentId;
    private String parentName;


    public String getParentName() {
        return parentName;
    }


    public void setParentName(String parentName) {
        this.parentName = parentName;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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


    public String getMemo() {
        return memo;
    }


    public void setMemo(String memo) {
        this.memo = memo;
    }


    public Long getParentId() {
        return parentId;
    }


    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
