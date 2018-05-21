package com.hangjia.bxj.newperson.model;

import java.io.Serializable;
import java.util.Date;

public class NewPersonCourseType implements Serializable {
    private Long id;

    private Long courseStageId;

    private String name;

    private String iconUrl;

    private Integer sort;

    private Integer enableStatus;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseStageId() {
        return courseStageId;
    }

    public void setCourseStageId(Long courseStageId) {
        this.courseStageId = courseStageId;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
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
        sb.append(", courseStageId=").append(courseStageId);
        sb.append(", name=").append(name);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", sort=").append(sort);
        sb.append(", enableStatus=").append(enableStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


    /**************************** extend begin here *******************************/
    private String courseStageName;

    public String getCourseStageName() {
        return courseStageName;
    }

    public void setCourseStageName(String courseStageName) {
        this.courseStageName = courseStageName;
    }
}