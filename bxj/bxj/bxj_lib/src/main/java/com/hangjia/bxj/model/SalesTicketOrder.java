package com.hangjia.bxj.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
public class SalesTicketOrder {
    private Long fid;

    private Integer basicId;

    private Integer sitId;

    private Integer salesmanId;

    private String orderno;

    private String pipeno;

    private Integer num;

    private BigDecimal money;

    private Integer state;

    private Integer payment;

    private Date payTime;

    private Integer attendNum;

    private Date createTime;

    private Date updateTime;
    
    private Integer inputSource; 
    private String tel;

    private String channelId;
    
    private Integer free;
    
    private Integer actual;

    
    private Integer sendInfo;
    private Integer ticketType;
    
    
    private List<String> phones; 
    private List<SalesTicketOrderDetail> orderDetails;
	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public Integer getBasicId() {
		return basicId;
	}

	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}

	public Integer getSitId() {
		return sitId;
	}

	public void setSitId(Integer sitId) {
		this.sitId = sitId;
	}

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getPipeno() {
		return pipeno;
	}

	public void setPipeno(String pipeno) {
		this.pipeno = pipeno;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public Integer getActual() {
		return actual;
	}

	public void setActual(Integer actual) {
		this.actual = actual;
	}

	public Integer getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(Integer sendInfo) {
		this.sendInfo = sendInfo;
	}

	public Integer getTicketType() {
		return ticketType;
	}

	public void setTicketType(Integer ticketType) {
		this.ticketType = ticketType;
	}

	public List<SalesTicketOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<SalesTicketOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Integer getInputSource() {
		return inputSource;
	}

	public void setInputSource(Integer inputSource) {
		this.inputSource = inputSource;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
	
}