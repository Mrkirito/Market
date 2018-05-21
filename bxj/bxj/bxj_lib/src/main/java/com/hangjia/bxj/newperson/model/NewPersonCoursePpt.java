package com.hangjia.bxj.newperson.model;

import java.io.Serializable;
import java.util.Date;

public class NewPersonCoursePpt implements Serializable {
    private Long id;

    private Long courseId;

    private String pptUrl;

    private String paragraphTitle;

    private String paragraphContext;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getPptUrl() {
        return pptUrl;
    }

    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl == null ? null : pptUrl.trim();
    }

    public String getParagraphTitle() {
        return paragraphTitle;
    }

    public void setParagraphTitle(String paragraphTitle) {
        this.paragraphTitle = paragraphTitle == null ? null : paragraphTitle.trim();
    }

    public String getParagraphContext() {
        return paragraphContext;
    }

    public void setParagraphContext(String paragraphContext) {
        this.paragraphContext = paragraphContext == null ? null : paragraphContext.trim();
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
        sb.append(", courseId=").append(courseId);
        sb.append(", pptUrl=").append(pptUrl);
        sb.append(", paragraphTitle=").append(paragraphTitle);
        sb.append(", paragraphContext=").append(paragraphContext);
        sb.append(", sort=").append(sort);
        sb.append(", enableStatus=").append(enableStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}