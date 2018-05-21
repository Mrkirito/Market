package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class BxjMyConf extends BaseModel implements Serializable {
    private Long id;

    private Long parentId;

    private String name;

    private String iconUrl;

    private String pageUrl;

    private String description;

    private Boolean isDisplay;

    private Integer groupType;

    private Integer sort;

    private Boolean isDisplayNew;

    private Integer confType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsDisplayNew() {
        return isDisplayNew;
    }

    public void setIsDisplayNew(Boolean isDisplayNew) {
        this.isDisplayNew = isDisplayNew;
    }

    public Integer getConfType() {
        return confType;
    }

    public void setConfType(Integer confType) {
        this.confType = confType;
    }

    public Date getCreateTime() {
        return createTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", pageUrl=").append(pageUrl);
        sb.append(", description=").append(description);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", groupType=").append(groupType);
        sb.append(", sort=").append(sort);
        sb.append(", isDisplayNew=").append(isDisplayNew);
        sb.append(", confType=").append(confType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}