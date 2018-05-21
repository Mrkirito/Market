package com.hangjia.bxj.model.order;

import com.hangjia.bxj.common.BaseCommonDO;

public class Reward extends BaseCommonDO{
	private Long fid;
	private String orderId;
	private String payType;
	private Long videoId;
	private Long userId;
	private Double money;
	private String userName;
	private String nickName;
	private String videoName;
	private Integer userSum;
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Long getVideoId() {
		return videoId;
	}
	public Integer getUserSum() {
		return userSum;
	}
	public void setUserSum(Integer userSum) {
		this.userSum = userSum;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
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
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
}