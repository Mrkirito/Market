package com.core.cms.model.mapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradeInsureAppend implements Serializable {
    private Long fid;

    private Integer memberId;

    private Integer insureId;

    private String tradeId;

    private Integer isSubjoin;

    private String productCode;

    private Integer productId;

    private String productName;

    private String dutyCode;

    private Integer insureType;

    private Integer insureYears;

    private Integer payYearsType;

    private Integer payYears;

    private BigDecimal marketPrice;

    private BigDecimal salePrice;

    private BigDecimal marketAmount;

    private BigDecimal saleAmount;

    private Integer buyNumber;

    private BigDecimal totalAmount;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getInsureId() {
        return insureId;
    }

    public void setInsureId(Integer insureId) {
        this.insureId = insureId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public Integer getIsSubjoin() {
        return isSubjoin;
    }

    public void setIsSubjoin(Integer isSubjoin) {
        this.isSubjoin = isSubjoin;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode == null ? null : dutyCode.trim();
    }

    public Integer getInsureType() {
        return insureType;
    }

    public void setInsureType(Integer insureType) {
        this.insureType = insureType;
    }

    public Integer getInsureYears() {
        return insureYears;
    }

    public void setInsureYears(Integer insureYears) {
        this.insureYears = insureYears;
    }

    public Integer getPayYearsType() {
        return payYearsType;
    }

    public void setPayYearsType(Integer payYearsType) {
        this.payYearsType = payYearsType;
    }

    public Integer getPayYears() {
        return payYears;
    }

    public void setPayYears(Integer payYears) {
        this.payYears = payYears;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getMarketAmount() {
        return marketAmount;
    }

    public void setMarketAmount(BigDecimal marketAmount) {
        this.marketAmount = marketAmount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}