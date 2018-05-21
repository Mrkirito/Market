package com.hangjia.bxj.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopGoodsExpressPrice implements Serializable {
    private Long id;

    private Long goodsId;

    private String area;

    private BigDecimal firstHeavy;

    private BigDecimal firstHeavyPrice;

    private BigDecimal overHeavyPrice;

    private Date createTime;

    private Long createUser;

    private Date updateTime;

    private Long updateUesr;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public BigDecimal getFirstHeavy() {
        return firstHeavy;
    }

    public void setFirstHeavy(BigDecimal firstHeavy) {
        this.firstHeavy = firstHeavy;
    }

    public BigDecimal getFirstHeavyPrice() {
        return firstHeavyPrice;
    }

    public void setFirstHeavyPrice(BigDecimal firstHeavyPrice) {
        this.firstHeavyPrice = firstHeavyPrice;
    }

    public BigDecimal getOverHeavyPrice() {
        return overHeavyPrice;
    }

    public void setOverHeavyPrice(BigDecimal overHeavyPrice) {
        this.overHeavyPrice = overHeavyPrice;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", area=").append(area);
        sb.append(", firstHeavy=").append(firstHeavy);
        sb.append(", firstHeavyPrice=").append(firstHeavyPrice);
        sb.append(", overHeavyPrice=").append(overHeavyPrice);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUesr=").append(updateUesr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}