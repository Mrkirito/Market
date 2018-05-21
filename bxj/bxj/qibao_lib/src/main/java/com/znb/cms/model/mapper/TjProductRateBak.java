package com.znb.cms.model.mapper;

import java.math.BigDecimal;

public class TjProductRateBak {
    private Integer fid;

    private Integer productId;

    private Integer age;

    private Integer sex;

    private BigDecimal price;

    private BigDecimal fenshuinsured;

    private BigDecimal insured;

    private String paymentTerm;

    private Integer type;

    private String guaranteePeriod;

    private String elseTypeStr;

    private String subsidisedTypeStr;

    private Integer payType;

    private Integer guaranteeType;

    private Integer elseType;

    private Integer subsidisedType;

    private BigDecimal basicInsured;

    private BigDecimal basicPrice;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFenshuinsured() {
        return fenshuinsured;
    }

    public void setFenshuinsured(BigDecimal fenshuinsured) {
        this.fenshuinsured = fenshuinsured;
    }

    public BigDecimal getInsured() {
        return insured;
    }

    public void setInsured(BigDecimal insured) {
        this.insured = insured;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm == null ? null : paymentTerm.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(String guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod == null ? null : guaranteePeriod.trim();
    }

    public String getElseTypeStr() {
        return elseTypeStr;
    }

    public void setElseTypeStr(String elseTypeStr) {
        this.elseTypeStr = elseTypeStr == null ? null : elseTypeStr.trim();
    }

    public String getSubsidisedTypeStr() {
        return subsidisedTypeStr;
    }

    public void setSubsidisedTypeStr(String subsidisedTypeStr) {
        this.subsidisedTypeStr = subsidisedTypeStr == null ? null : subsidisedTypeStr.trim();
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

    public Integer getElseType() {
        return elseType;
    }

    public void setElseType(Integer elseType) {
        this.elseType = elseType;
    }

    public Integer getSubsidisedType() {
        return subsidisedType;
    }

    public void setSubsidisedType(Integer subsidisedType) {
        this.subsidisedType = subsidisedType;
    }

    public BigDecimal getBasicInsured() {
        return basicInsured;
    }

    public void setBasicInsured(BigDecimal basicInsured) {
        this.basicInsured = basicInsured;
    }

    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }
}