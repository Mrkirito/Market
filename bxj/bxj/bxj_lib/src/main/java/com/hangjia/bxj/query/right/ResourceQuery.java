package com.hangjia.bxj.query.right;

import com.hangjia.bxj.common.BaseCommonQuery;


/**
 * @author yaoy
 * @since 2016-06-14
 */
public class ResourceQuery extends BaseCommonQuery {
	
    private Integer status;
    private Integer parentId;
    private String resourceCode;
    private String resourceName;
    private String navCode;
    private Integer showNav;

    public Integer getShowNav() {
		return showNav;
	}


	public void setShowNav(Integer showNav) {
		this.showNav = showNav;
	}


	public String getNavCode() {
        return navCode;
    }


    public void setNavCode(String navCode) {
        this.navCode = navCode;
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


    public Integer getParentId() {
        return parentId;
    }


    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }
}
