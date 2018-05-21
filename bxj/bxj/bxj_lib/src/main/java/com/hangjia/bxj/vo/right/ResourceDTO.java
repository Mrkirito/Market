package com.hangjia.bxj.vo.right;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * @author yaoy
 * @since 2016-06-14
 */
public class ResourceDTO extends ResourceTreeNode {
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

    private List<String> idList;
    
	/**
     * resourceUrl 可以使用http://开头的地址，一般使用在外部系统连接上
     * @return
     */
    public boolean isStartHttp() {
        if (StringUtils.isNotBlank(resourceUrl) && resourceUrl.startsWith("http")) {
            return true;
        }
        return false;
    }


    public String getNavName() {
        return navName;
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

    private List<ResourceDTO> children;

    private List<FunctionDTO> functionList;

    private boolean active = false;
    private boolean show = false;

    public boolean isActive() {
        if (!active && null != children && children.size() > 0) {
            for (ResourceDTO child : children) {
                if (child.isActive()) {
                    active = true;
                    break;
                }
            }
        }
        return active;
    }


    public boolean isShow() {
        if ("1".equals(menuShow) && !show && null != children && children.size() > 0) {
            for (ResourceDTO child : children) {
                if (child.isShow()) {
                    show = true;
                    break;
                }
            }
        }
        return show;
    }


    public void setShow(boolean show) {
        this.show = show;
    }


    public void setActive(boolean active) {
        this.active = active;
    }


    public String getResourceIcon() {
        return resourceIcon;
    }


    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }


    public List<FunctionDTO> getFunctionList() {
        return functionList;
    }


    public void setFunctionList(List<FunctionDTO> functionList) {
        this.functionList = functionList;
    }


    public List<ResourceDTO> getChildren() {
        return children;
    }


    public void setChildren(List<ResourceDTO> children) {
        this.children = children;
    }


    public void addResource(ResourceDTO resourceDTO) {
        if (null == children) {
            children = new ArrayList<ResourceDTO>();
        }
        if (null != resourceDTO) {
            children.add(resourceDTO);
        }
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

    public List<String> getIdList() {
		return idList;
	}


	public void setIdList(List<String> idList) {
		this.idList = idList;
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
