package com.znb.cms.model.mapper;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInsure {
    private Integer id;

    private Integer productId;

    private Integer sex;

    private Integer maxAge;

    private Integer payType;

    private Integer guaranteeType;

    private Integer subsidisedType;

    private Integer elseType;

    private Integer calculateType;

    private BigDecimal coverage;

    private BigDecimal basicCoverage;

    private Integer defaultState;

    private Integer sort;

    private BigDecimal pieceCoverage;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(Integer guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public Integer getSubsidisedType() {
        return subsidisedType;
    }

    public void setSubsidisedType(Integer subsidisedType) {
        this.subsidisedType = subsidisedType;
    }

    public Integer getElseType() {
        return elseType;
    }

    public void setElseType(Integer elseType) {
        this.elseType = elseType;
    }

    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

    public BigDecimal getCoverage() {
        return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
        this.coverage = coverage;
    }

    public BigDecimal getBasicCoverage() {
        return basicCoverage;
    }

    public void setBasicCoverage(BigDecimal basicCoverage) {
        this.basicCoverage = basicCoverage;
    }

    public Integer getDefaultState() {
        return defaultState;
    }

    public void setDefaultState(Integer defaultState) {
        this.defaultState = defaultState;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public BigDecimal getPieceCoverage() {
        return pieceCoverage;
    }

    public void setPieceCoverage(BigDecimal pieceCoverage) {
        this.pieceCoverage = pieceCoverage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}