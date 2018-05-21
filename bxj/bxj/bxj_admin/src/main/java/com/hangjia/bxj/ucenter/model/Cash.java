package com.hangjia.bxj.ucenter.model;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonDO;

public class Cash extends BaseCommonDO{
	private Long id;
	private String orderNo;
	private Long userId;
	private Long bankCardId;
	private Double sumAmount;
	private Double expectedAmount;
	private Double fee;
	private Integer auditStatus;
	private Date auditTime;
	private String auditReason;
	private Date createTime;
	private Date updateTime;
	
	private String bankName;
	private String bankNo;
	private String bankProvince;
	private String bankCounty;
	private String bankCity;
	private String bankBranchName;	
	private String bankUserName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(Long bankCardId) {
		this.bankCardId = bankCardId;
	}
	public Double getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}
	public Double getExpectedAmount() {
		return expectedAmount;
	}
	public void setExpectedAmount(Double expectedAmount) {
		this.expectedAmount = expectedAmount;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditReason() {
		return auditReason;
	}
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCounty() {
		return bankCounty;
	}
	public void setBankCounty(String bankCounty) {
		this.bankCounty = bankCounty;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	
}