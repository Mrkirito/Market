package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class BxjAppProductData implements Serializable {
    private Long id;

    private Date dataDate;

    private Long planPv;

    private Long planUv;

    private Long planMakePopulation;

    private Long planMakeCount;

    private Long studyNewPv;

    private Long studyPv;

    private Long studyUv;

    private Long friendPv;

    private Long friendUv;

    private Long friendShareCount;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public Long getPlanPv() {
        return planPv;
    }

    public void setPlanPv(Long planPv) {
        this.planPv = planPv;
    }

    public Long getPlanUv() {
        return planUv;
    }

    public void setPlanUv(Long planUv) {
        this.planUv = planUv;
    }

    public Long getPlanMakePopulation() {
        return planMakePopulation;
    }

    public void setPlanMakePopulation(Long planMakePopulation) {
        this.planMakePopulation = planMakePopulation;
    }

    public Long getPlanMakeCount() {
        return planMakeCount;
    }

    public void setPlanMakeCount(Long planMakeCount) {
        this.planMakeCount = planMakeCount;
    }

    public Long getStudyNewPv() {
        return studyNewPv;
    }

    public void setStudyNewPv(Long studyNewPv) {
        this.studyNewPv = studyNewPv;
    }

    public Long getStudyPv() {
        return studyPv;
    }

    public void setStudyPv(Long studyPv) {
        this.studyPv = studyPv;
    }

    public Long getStudyUv() {
        return studyUv;
    }

    public void setStudyUv(Long studyUv) {
        this.studyUv = studyUv;
    }

    public Long getFriendPv() {
        return friendPv;
    }

    public void setFriendPv(Long friendPv) {
        this.friendPv = friendPv;
    }

    public Long getFriendUv() {
        return friendUv;
    }

    public void setFriendUv(Long friendUv) {
        this.friendUv = friendUv;
    }

    public Long getFriendShareCount() {
        return friendShareCount;
    }

    public void setFriendShareCount(Long friendShareCount) {
        this.friendShareCount = friendShareCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dataDate=").append(dataDate);
        sb.append(", planPv=").append(planPv);
        sb.append(", planUv=").append(planUv);
        sb.append(", planMakePopulation=").append(planMakePopulation);
        sb.append(", planMakeCount=").append(planMakeCount);
        sb.append(", studyNewPv=").append(studyNewPv);
        sb.append(", studyPv=").append(studyPv);
        sb.append(", studyUv=").append(studyUv);
        sb.append(", friendPv=").append(friendPv);
        sb.append(", friendUv=").append(friendUv);
        sb.append(", friendShareCount=").append(friendShareCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}