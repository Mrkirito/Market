package com.hangjia.bxj.model.tjgl;

import java.io.Serializable;
import java.util.Date;

public class XrtStatisticsData implements Serializable {
    private Long id;

    private Date dataTime;

    private Integer studyCount;

    private Integer testCount;

    private Integer clearanceCount;

    private Integer studyCountUv;

    private Integer testCountUv;

    private Integer clearanceCountUv;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getStudyCountUv() {
        return studyCountUv;
    }

    public void setStudyCountUv(Integer studyCountUv) {
        this.studyCountUv = studyCountUv;
    }

    public Integer getTestCountUv() {
        return testCountUv;
    }

    public void setTestCountUv(Integer testCountUv) {
        this.testCountUv = testCountUv;
    }

    public Integer getClearanceCountUv() {
        return clearanceCountUv;
    }

    public void setClearanceCountUv(Integer clearanceCountUv) {
        this.clearanceCountUv = clearanceCountUv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(Integer studyCount) {
        this.studyCount = studyCount;
    }

    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }

    public Integer getClearanceCount() {
        return clearanceCount;
    }

    public void setClearanceCount(Integer clearanceCount) {
        this.clearanceCount = clearanceCount;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
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
        sb.append(", dataTime=").append(dataTime);
        sb.append(", studyCount=").append(studyCount);
        sb.append(", testCount=").append(testCount);
        sb.append(", clearanceCount=").append(clearanceCount);
        sb.append(", createName=").append(createName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateName=").append(updateName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}