package com.znb.cms.model.mapper;

import java.util.Date;

public class ProductRateFactor {
    private Integer id;

    private Integer factorType;

    private String factorName;

    private Integer factorNumber;

    private Integer factorCal;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFactorType() {
        return factorType;
    }

    public void setFactorType(Integer factorType) {
        this.factorType = factorType;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName == null ? null : factorName.trim();
    }

    public Integer getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(Integer factorNumber) {
        this.factorNumber = factorNumber;
    }

    public Integer getFactorCal() {
        return factorCal;
    }

    public void setFactorCal(Integer factorCal) {
        this.factorCal = factorCal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}