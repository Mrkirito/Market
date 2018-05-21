package com.hangjia.bxj.query.ticket;

import com.hangjia.bxj.common.BaseCommonQuery;

public class SalesTicketQuery extends BaseCommonQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2938731751651880360L;
	private String startTime;
	private String endTime;
	private Integer channelId;
	private Integer basicId;
	private Integer payType;
	private String customerTel;
	
	private Integer attendState;
	private Long orderId;
	
	private String userPhone;
	private String userName;
	
	private Integer state;
	private String buyTel;
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getBasicId() {
		return basicId;
	}
	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAttendState() {
		return attendState;
	}
	public void setAttendState(Integer attendState) {
		this.attendState = attendState;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getBuyTel() {
		return buyTel;
	}
	public void setBuyTel(String buyTel) {
		this.buyTel = buyTel;
	}	
	
}
