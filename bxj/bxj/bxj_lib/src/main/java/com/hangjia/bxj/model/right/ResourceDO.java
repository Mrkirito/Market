package com.hangjia.bxj.model.right;

import java.io.Serializable;
import java.util.Date;

public class ResourceDO implements Serializable {
    private Long id;
    private String resourceCode;// 权限编码
    private String resourceName;// 资源权限名称
    private String resourceUrl;// 权限url
    private String resourceIcon;// 权限图标
    private Integer permissionControl;// 是否要权限控制 1：需要权限控制 0：不需要权限控制
    private String menuShow;// 是否菜单，即是否现在系统左侧菜单 1：是 0：否
    private Integer sort;
    private Integer parentId;
    private String parentName;
    private String navCode;
    private String navName;

    private Integer status;
    private Date createTime;
    private Date updateTime;
    private String createName;
    private String updateName;

    public String getNavName() {
        return navName;
    }

    public Date getCreateTime() {
		return createTime;
	}
    
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public void setNavName(String navName) {
        this.navName = navName;
    }


    public String getNavCode() {
        return navCode;
    }


    public void setNavCode(String navCode) {
        this.navCode = navCode;
    }


    public String getResourceIcon() {
        return resourceIcon;
    }


    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }


    public String getParentName() {
        return parentName;
    }


    public void setParentName(String parentName) {
        this.parentName = parentName;
    }


    public Integer getSort() {
        return sort;
    }


    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public Integer getParentId() {
        return parentId;
    }


    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getResourceCode() {
        return resourceCode;
    }


    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }


    public String getResourceName() {
        return resourceName;
    }


    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }


    public String getResourceUrl() {
        return resourceUrl;
    }


    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }


    public Integer getPermissionControl() {
        return permissionControl;
    }


    public void setPermissionControl(Integer permissionControl) {
        this.permissionControl = permissionControl;
    }


    public String getMenuShow() {
        return menuShow;
    }


    public void setMenuShow(String menuShow) {
        this.menuShow = menuShow;
    }

}