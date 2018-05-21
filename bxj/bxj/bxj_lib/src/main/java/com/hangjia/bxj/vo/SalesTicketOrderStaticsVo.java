package com.hangjia.bxj.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SalesTicketOrderStaticsVo {
	private String activityName;
	private String salesmanName;
	private Integer totalNum;	//总计销量
	private Integer actualNum;	//实际销量
	private Integer presentNum; //赠票量
	private BigDecimal totalMoney;//总金额
	private Integer payType;	  //支付方式		
	private Integer basicId;	  //会议Id
	private Date payTime;
	private String customerTel;   //购买人电话

	
	private Long orderId;
	
	private Integer attendState;//全填完=1，非=2
	private Integer exitAttend;//剩余没填的
	
	
	private String address;
	private Date beginTime;
	private Date endTime;


	/***
	 * 
	 */
	private Integer surplusTicketsNum;
	private Integer totalTicketsNum;
	

	
	private Integer totalNum0=0; // 总计销量
	private Integer actualNum0=0; //实际销量
	private Integer presentNum0=0; // 赠票量
	private BigDecimal totalMoney0=new BigDecimal(0);//总金额
	
	private Integer totalNum3=0; // 线下总计销量
	private Integer actualNum3=0; // 线下实际销量
	private Integer presentNum3=0; // 线下赠票量
	private BigDecimal totalMoney3=new BigDecimal(0);// 线下总金额

	private Integer totalNum2=0; // 支付宝总计销量
	private Integer actualNum2=0; // 支付宝实际销量
	private Integer presentNum2=0; // 支付宝赠票量
	private BigDecimal totalMoney2=new BigDecimal(0);// 支付宝总金额
	
	private Integer totalNum1=0; // 微信总计销量
	private Integer actualNum1=0; // 微信实际销量
	private Integer presentNum1=0; // 微信赠票量
	private BigDecimal totalMoney1=new BigDecimal(0);// 微信总金额
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getActualNum() {
		return actualNum;
	}
	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}
	public Integer getPresentNum() {
		return presentNum;
	}
	public void setPresentNum(Integer presentNum) {
		this.presentNum = presentNum;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getSurplusTicketsNum() {
		return surplusTicketsNum;
	}
	public void setSurplusTicketsNum(Integer surplusTicketsNum) {
		this.surplusTicketsNum = surplusTicketsNum;
	}
	public Integer getTotalTicketsNum() {
		return totalTicketsNum;
	}
	public void setTotalTicketsNum(Integer totalTicketsNum) {
		this.totalTicketsNum = totalTicketsNum;
	}
	public Integer getBasicId() {
		return basicId;
	}
	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}
	public Integer getTotalNum3() {
		return totalNum3;
	}
	public void setTotalNum3(Integer totalNum3) {
		this.totalNum3 = totalNum3;
	}
	public Integer getActualNum3() {
		return actualNum3;
	}
	public void setActualNum3(Integer actualNum3) {
		this.actualNum3 = actualNum3;
	}
	public Integer getPresentNum3() {
		return presentNum3;
	}
	public void setPresentNum3(Integer presentNum3) {
		this.presentNum3 = presentNum3;
	}
	public BigDecimal getTotalMoney3() {
		return totalMoney3;
	}
	public void setTotalMoney3(BigDecimal totalMoney3) {
		this.totalMoney3 = totalMoney3;
	}
	public Integer getTotalNum2() {
		return totalNum2;
	}
	public void setTotalNum2(Integer totalNum2) {
		this.totalNum2 = totalNum2;
	}
	public Integer getActualNum2() {
		return actualNum2;
	}
	public void setActualNum2(Integer actualNum2) {
		this.actualNum2 = actualNum2;
	}
	public Integer getPresentNum2() {
		return presentNum2;
	}
	public void setPresentNum2(Integer presentNum2) {
		this.presentNum2 = presentNum2;
	}
	public BigDecimal getTotalMoney2() {
		return totalMoney2;
	}
	public void setTotalMoney2(BigDecimal totalMoney2) {
		this.totalMoney2 = totalMoney2;
	}
	public Integer getTotalNum1() {
		return totalNum1;
	}
	public void setTotalNum1(Integer totalNum1) {
		this.totalNum1 = totalNum1;
	}
	public Integer getActualNum1() {
		return actualNum1;
	}
	public void setActualNum1(Integer actualNum1) {
		this.actualNum1 = actualNum1;
	}
	public Integer getPresentNum1() {
		return presentNum1;
	}
	public void setPresentNum1(Integer presentNum1) {
		this.presentNum1 = presentNum1;
	}
	public BigDecimal getTotalMoney1() {
		return totalMoney1;
	}
	public void setTotalMoney1(BigDecimal totalMoney1) {
		this.totalMoney1 = totalMoney1;
	}
	public Integer getTotalNum0() {
		return totalNum0;
	}
	public void setTotalNum0(Integer totalNum0) {
		this.totalNum0 = totalNum0;
	}
	public Integer getActualNum0() {
		return actualNum0;
	}
	public void setActualNum0(Integer actualNum0) {
		this.actualNum0 = actualNum0;
	}
	public Integer getPresentNum0() {
		return presentNum0;
	}
	public void setPresentNum0(Integer presentNum0) {
		this.presentNum0 = presentNum0;
	}
	public BigDecimal getTotalMoney0() {
		return totalMoney0;
	}
	public void setTotalMoney0(BigDecimal totalMoney0) {
		this.totalMoney0 = totalMoney0;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
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
	public Integer getAttendState() {
		return attendState;
	}
	public void setAttendState(Integer attendState) {
		this.attendState = attendState;
	}
	public Integer getExitAttend() {
		return exitAttend;
	}
	public void setExitAttend(Integer exitAttend) {
		this.exitAttend = exitAttend;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
}
