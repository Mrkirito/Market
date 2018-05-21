package com.hangjia.bxj.ucenter.model;

import com.hangjia.bxj.common.BaseCommonDO;

public class Profit extends BaseCommonDO{
	private Long id;
	private String orderNo;
	private Long userId;
	private Double amount;
	private Integer type;
	private Integer method;
	private Double sumAmount;
	private Double feeRate;
	private Double proportionRate;
	private Double taxRate;
	private String userName;
	private String nickName;
	private Double usableAmount;
	private String videoName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Double getUsableAmount() {
		return usableAmount;
	}
	public void setUsableAmount(Double usableAmount) {
		this.usableAmount = usableAmount;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	public Double getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}
	public Double getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}
	public Double getProportionRate() {
		return proportionRate;
	}
	public void setProportionRate(Double proportionRate) {
		this.proportionRate = proportionRate;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
}