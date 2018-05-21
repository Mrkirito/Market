/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.query.shop;

import com.hangjia.bxj.common.BaseCommonQuery;

import java.util.Date;

/**
 * 订单详情查询条件
 * Created by Caigp
 * Date: 2016/11/2 14:57
 */
public class ShopOrdersQuery extends BaseCommonQuery{

    /**
     * 起止时间
     */
    private Date startTime;
    /**
     * 起止时间
     */
    private Date endTime;
    /**
     * 业务订单ID
     */
    private String orderId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 支付方式
     */
    private String payType;

    private Long goodsId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}