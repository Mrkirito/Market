package com.hangjia.bxj.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SalesTicketOrderAttendPeoplesVo {
	private Long fid;
	private Long orderId;
	private Integer basicId;
	private String userName;
	private String userPhone;
	private Integer state;
	private String floor;
	private String area;
	private String rows;
	private String number;
	private Date payTime;
	private String manName;
	private String activityName;
	private String sitName;
	private BigDecimal price;
	private String company;
	private String businessHall;

	private String address;

	private Date beginTime;
	private Date endTime;
	private Integer sendInfo;
	
	private String tel;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBusinessHall() {
		return businessHall;
	}

	public void setBusinessHall(String businessHall) {
		this.businessHall = businessHall;
	}

	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getBasicId() {
		return basicId;
	}
	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getManName() {
		return manName;
	}
	public void setManName(String manName) {
		this.manName = manName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getSitName() {
		return sitName;
	}
	public void setSitName(String sitName) {
		this.sitName = sitName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getSendInfo() {
		return sendInfo;
	}
	public void setSendInfo(Integer sendInfo) {
		this.sendInfo = sendInfo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

		

}
