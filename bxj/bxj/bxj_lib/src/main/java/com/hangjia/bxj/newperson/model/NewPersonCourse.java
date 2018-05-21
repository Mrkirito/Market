package com.hangjia.bxj.newperson.model;

import java.io.Serializable;
import java.util.Date;

public class NewPersonCourse implements Serializable {
    private Long id;

    private Long courseTypeId;

    private String courseTitle;

    private String courseSubtitle;

    private String iconUrl;

    private String suggestStudyTime;

    private Long studyPerson;

    private Long virtualStudyPerson;

    private Long testPerson;

    private Long virtualTestPerson;

    private Long passPerson;

    private Long virtualPassPerson;

    private Integer difficultyStar;

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

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle == null ? null : courseTitle.trim();
    }

    public String getCourseSubtitle() {
        return courseSubtitle;
    }

    public void setCourseSubtitle(String courseSubtitle) {
        this.courseSubtitle = courseSubtitle == null ? null : courseSubtitle.trim();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getSuggestStudyTime() {
        return suggestStudyTime;
    }

    public void setSuggestStudyTime(String suggestStudyTime) {
        this.suggestStudyTime = suggestStudyTime == null ? null : suggestStudyTime.trim();
    }

    public Long getStudyPerson() {
        return studyPerson;
    }

    public void setStudyPerson(Long studyPerson) {
        this.studyPerson = studyPerson;
    }

    public Long getVirtualStudyPerson() {
        return virtualStudyPerson;
    }

    public void setVirtualStudyPerson(Long virtualStudyPerson) {
        this.virtualStudyPerson = virtualStudyPerson;
    }

    public Long getTestPerson() {
        return testPerson;
    }

    public void setTestPerson(Long testPerson) {
        this.testPerson = testPerson;
    }

    public Long getVirtualTestPerson() {
        return virtualTestPerson;
    }

    public void setVirtualTestPerson(Long virtualTestPerson) {
        this.virtualTestPerson = virtualTestPerson;
    }

    public Long getPassPerson() {
        return passPerson;
    }

    public void setPassPerson(Long passPerson) {
        this.passPerson = passPerson;
    }

    public Long getVirtualPassPerson() {
        return virtualPassPerson;
    }

    public void setVirtualPassPerson(Long virtualPassPerson) {
        this.virtualPassPerson = virtualPassPerson;
    }

    public Integer getDifficultyStar() {
        return difficultyStar;
    }

    public void setDifficultyStar(Integer difficultyStar) {
        this.difficultyStar = difficultyStar;
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
        sb.append(", courseTypeId=").append(courseTypeId);
        sb.append(", courseTitle=").append(courseTitle);
        sb.append(", courseSubtitle=").append(courseSubtitle);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", suggestStudyTime=").append(suggestStudyTime);
        sb.append(", studyPerson=").append(studyPerson);
        sb.append(", virtualStudyPerson=").append(virtualStudyPerson);
        sb.append(", testPerson=").append(testPerson);
        sb.append(", virtualTestPerson=").append(virtualTestPerson);
        sb.append(", passPerson=").append(passPerson);
        sb.append(", virtualPassPerson=").append(virtualPassPerson);
        sb.append(", difficultyStar=").append(difficultyStar);
        sb.append(", sort=").append(sort);
        sb.append(", enableStatus=").append(enableStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**************************** extend begin here *******************************/
    private String courseTypeName;

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }
}