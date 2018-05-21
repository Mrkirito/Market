package com.hangjia.bxj.model;

import java.util.Date;

public class PlanIntelligent {
    private Long id;

    private Integer sex;

    private Integer ageMin;

    private Integer ageMax;

    private Integer premiumMin;

    private Integer premiumMax;

    private Integer insureType;

    private Integer pid;

    private Integer status;

    private Integer jfnx;

    private Integer bxnx;

    private Integer factor5;

    private Integer lqnl;

    private Integer bzlq;

    private Integer sort;

    private Integer group;

    private Integer calFlag;

    private Integer be;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    
    /*************extend fields**********************/
    private Integer bf;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public Integer getPremiumMin() {
        return premiumMin;
    }

    public void setPremiumMin(Integer premiumMin) {
        this.premiumMin = premiumMin;
    }

    public Integer getPremiumMax() {
        return premiumMax;
    }

    public void setPremiumMax(Integer premiumMax) {
        this.premiumMax = premiumMax;
    }

    public Integer getInsureType() {
        return insureType;
    }

    public void setInsureType(Integer insureType) {
        this.insureType = insureType;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJfnx() {
        return jfnx;
    }

    public void setJfnx(Integer jfnx) {
        this.jfnx = jfnx;
    }

    public Integer getBxnx() {
        return bxnx;
    }

    public void setBxnx(Integer bxnx) {
        this.bxnx = bxnx;
    }

    public Integer getFactor5() {
        return factor5;
    }

    public void setFactor5(Integer factor5) {
        this.factor5 = factor5;
    }

    public Integer getLqnl() {
        return lqnl;
    }

    public void setLqnl(Integer lqnl) {
        this.lqnl = lqnl;
    }

    public Integer getBzlq() {
        return bzlq;
    }

    public void setBzlq(Integer bzlq) {
        this.bzlq = bzlq;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getCalFlag() {
        return calFlag;
    }

    public void setCalFlag(Integer calFlag) {
        this.calFlag = calFlag;
    }

    public Integer getBe() {
        return be;
    }

    public void setBe(Integer be) {
        this.be = be;
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

	public Integer getBf() {
		return bf;
	}

	public void setBf(Integer bf) {
		this.bf = bf;
	}
    
}