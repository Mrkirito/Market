package com.hangjia.bxj.model;

import java.util.Date;

public class PlanIntelligentChild {
    private Long id;

    private Long parentId;

    private Integer pid;

    private Integer status;

    private Integer jfnx;

    private Integer bxnx;

    private Integer factor5;

    private Integer lqnl;

    private Integer bzlq;

    private Integer calFlag;

    private Integer be;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

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
}