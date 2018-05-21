package com.znb.cms.model.mapper;

import java.math.BigDecimal;
import java.util.Date;

public class ProductRate {
    private Long id;

    private Integer productId;

    private Integer age;

    private Integer sex;

    private Integer payType;

    private Integer guaranteeType;

    private Integer subsidisedType;

    private Integer elseType;

    private BigDecimal insurancePremium;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public BigDecimal getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(BigDecimal insurancePremium) {
        this.insurancePremium = insurancePremium;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}