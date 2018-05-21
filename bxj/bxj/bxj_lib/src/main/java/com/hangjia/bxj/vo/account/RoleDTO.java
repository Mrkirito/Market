package com.hangjia.bxj.vo.account;


import java.util.ArrayList;
import java.util.List;

import com.hangjia.bxj.common.TreeNode;

/**
 * @author yaoy
 * @since 2016-06-17
 */
public class RoleDTO extends TreeNode {
    private Long id;
    private String roleCode;
    private String roleName;
    private String memo;
    private Long parentId;
    private String parentName;

    private List<RoleDTO> children;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<RoleDTO> getChildren() {
        return children;
    }

    public void setChildren(List<RoleDTO> children) {
        this.children = children;
    }

    public void addRole(RoleDTO roleDTO) {
        if(null == children) {
            children = new ArrayList<RoleDTO>();
        }
        if(null != roleDTO) {
            children.add(roleDTO);
        }
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
