package com.core.cms.model.mapper;

import com.core.cms.common.BaseCommonQuery;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/31.
 */
public class InsureBook extends BaseCommonQuery implements Serializable {
    private Long id;
    private String productName;
    private Integer productId;
    private String firmName;
    private String customerName;
    private String customerPhone;
    private String customerMark;
    private Long userId;
    private Integer dealState;
    private String dealMsg;
    private String customerIp;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getFirm_name() {
        return firmName;
    }

    public void setFirm_name(String firm_name) {
        this.firmName = firm_name;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerMark() {
        return customerMark;
    }

    public void setCustomerMark(String customerMark) {
        this.customerMark = customerMark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDealState() {
        return dealState;
    }

    public void setDealState(Integer dealState) {
        this.dealState = dealState;
    }

    public String getDealMsg() {
        return dealMsg;
    }

    public void setDealMsg(String dealMsg) {
        this.dealMsg = dealMsg;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
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
}
