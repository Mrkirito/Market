package com.znb.cms.model.mapper;

import java.math.BigDecimal;
import java.util.Date;

import com.znb.cms.common.BaseCommonQuery;

/**
 * 
 * @author yx 订单信息表
 */
public class Order extends BaseCommonQuery {
	private Integer id;

	private String insuranceName;// 保险名称

	private String beginDate;// 起保日期

	private String beginTime;// 起保时间

	private String endDate; // 终止日期

	private String endTime; // 终止时间

	private String duration; // 保险周期

	private String orderNo;// 订单编号

	private String orderState; // 订单状态

	private BigDecimal price;// 订单金额

	private Byte isDelete;// 是否删除

	private Date createTime;// 订单创建日期

	private String employeeIds;// 订单当前雇员id

	private Integer insuranceId;// 保险id

	private Integer userId;// 用户Id

	private Integer isurantId;// 投保人Id

	private Integer insureId;// 被投保人Id
	private Integer takeEffect;// 是否允许生效 0 否 1是

	private Date updateTime; // 订单更新时间
	private Date payTime; // 支付时间
	private Integer pid; // 来源订单
	private String policyNumber; // 保单号
	private String transferAccountName; 	 //转账账户名
	private String policyUrl; 	 //保单下载地址
	private String refundTime; //退款时间
	
	private String isurantMessage;//被保险人公司是否错误信息
	
	private String insureMessage;//投保人公司是否错误信息
	private String cancelReason;//取消原因

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName == "" ? null : insuranceName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate == "" ? null : beginDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate == "" ? null : endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == "" ? null : orderNo;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState == "" ? null : orderState;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsurantId() {
		return isurantId;
	}

	public void setIsurantId(Integer isurantId) {
		this.isurantId = isurantId;
	}

	public Integer getInsureId() {
		return insureId;
	}

	public void setInsureId(Integer insureId) {
		this.insureId = insureId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber == "" ? null :policyNumber ;
	}

	public String getTransferAccountName() {
		return transferAccountName;
	}

	public void setTransferAccountName(String transferAccountName) {
		this.transferAccountName = transferAccountName;
	}

	public String getPolicyUrl() {
		return policyUrl;
	}

	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}

	public String getIsurantMessage() {
		return isurantMessage;
	}

	public void setIsurantMessage(String isurantMessage) {
		this.isurantMessage = isurantMessage;
	}

	public String getInsureMessage() {
		return insureMessage;
	}

	public void setInsureMessage(String insureMessage) {
		this.insureMessage = insureMessage;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason == "" ? null :cancelReason ;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime == "" ?null:refundTime;
	}

	public Integer getTakeEffect() {
		return takeEffect;
	}

	public void setTakeEffect(Integer takeEffect) {
		this.takeEffect = takeEffect;
	}
	
	
}