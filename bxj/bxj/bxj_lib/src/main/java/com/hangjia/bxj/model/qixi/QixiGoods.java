package com.hangjia.bxj.model.qixi;

import java.math.BigDecimal;
import java.util.Date;

public class QixiGoods {
    private Long id;

    private String name;

    private Boolean isDisplay;

    private String picture;

    private BigDecimal price1;

    private BigDecimal price2;

    private BigDecimal price3;

    private Integer saleCount;

    private Integer falseSaleCount;

    private String description;

    private String discountDesc;

    private String purchaseProcessDesc;

    private Date createTime;

    private Long createUser;

    private Date updateTime;

    private Long updateUesr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    public BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(BigDecimal price3) {
        this.price3 = price3;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getFalseSaleCount() {
        return falseSaleCount;
    }

    public void setFalseSaleCount(Integer falseSaleCount) {
        this.falseSaleCount = falseSaleCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc == null ? null : discountDesc.trim();
    }

    public String getPurchaseProcessDesc() {
        return purchaseProcessDesc;
    }

    public void setPurchaseProcessDesc(String purchaseProcessDesc) {
        this.purchaseProcessDesc = purchaseProcessDesc == null ? null : purchaseProcessDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUesr() {
        return updateUesr;
    }

    public void setUpdateUesr(Long updateUesr) {
        this.updateUesr = updateUesr;
    }
}