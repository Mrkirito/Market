package com.hangjia.bxj.model.shop;

import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopOrders implements Serializable {
    private Long id;

    private String orderId;

    private String payOrderId;

    private Long userId;

    private Long goodsId;

    private Integer quantity;

    private String payType;

    private BigDecimal totalWeight;

    private BigDecimal goodsMoney;

    private BigDecimal expressMoney;

    private BigDecimal discountMoeny;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal totalMoney;

    private Integer status;

    private Long version;

    private Date createTime;

    private Date updateTime;

    private ShopOrdersDetail shopOrdersDetail;

    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public ShopOrdersDetail getShopOrdersDetail() {
        return shopOrdersDetail;
    }

    public void setShopOrdersDetail(ShopOrdersDetail shopOrdersDetail) {
        this.shopOrdersDetail = shopOrdersDetail;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        //支付方式：ALIPAY：APP支付宝;WX：APP微信支付；WX_JSAPI：公众号
        this.payType = payType == null ? null : payType.equals("WX")?"APP微信支付":payType.equals("WX_JSAPI")?"公众号":payType.equals("ALIPAY")?"APP支付宝":"未知";
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(BigDecimal goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public BigDecimal getExpressMoney() {
        return expressMoney;
    }

    public void setExpressMoney(BigDecimal expressMoney) {
        this.expressMoney = expressMoney;
    }

    public BigDecimal getDiscountMoeny() {
        return discountMoeny;
    }

    public void setDiscountMoeny(BigDecimal discountMoeny) {
        this.discountMoeny = discountMoeny;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", payOrderId=").append(payOrderId);
        sb.append(", userId=").append(userId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", quantity=").append(quantity);
        sb.append(", payType=").append(payType);
        sb.append(", totalWeight=").append(totalWeight);
        sb.append(", goodsMoney=").append(goodsMoney);
        sb.append(", expressMoney=").append(expressMoney);
        sb.append(", discountMoeny=").append(discountMoeny);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}