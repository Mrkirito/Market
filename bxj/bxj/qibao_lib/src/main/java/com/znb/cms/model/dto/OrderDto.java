package com.znb.cms.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import com.znb.cms.common.BaseCommonQuery;
import com.znb.cms.model.mapper.Insurance;
import com.znb.cms.model.mapper.Insure;
import com.znb.cms.model.mapper.Isurant;
import com.znb.cms.model.mapper.UserInfo;



/**
 * 订单
 * @author fhj
 * @date 2017年3月17日 下午2:23:31
 * @version <b>1.0.0</b>
 */
public class OrderDto extends BaseCommonQuery implements Serializable , Cloneable{
	private static final long serialVersionUID = 4230393032593230551L;
	
	private Integer id;
	private String insuranceName;//保险名称
	private String beginDate;	 //起保日期
	private String beginTime;	 //起保时间
	private String endDate;		 //终止日期
	private String endTime;		 //终止时间
	private String duration;	 //保险周期
	private String orderNo; 	 //订单编号
	private Integer takeEffect;// 是否允许生效 0 否 1是
	private String transferAccountName; 	 //转账账户名
	private String orderState;	 //订单状态 1、未完善   2、待审核   3、待支付   4、已取消   5、已承保   6、已生效   7、已失效 
	private Integer amount;		 //保额
	private BigDecimal price;	 //订单金额
	private String employeeIds;  //订单当前雇员id
	private Insurance insurance; //保险
	private UserInfo userInfo;	 //用户
	private Insure insure;		 //投保人
	private Isurant isurant;	 //被投保人
	private Boolean isDelete = false;//是否删除
	private Date updateTime;	 //订单更新时间
	private Date createTime;	 //订单创建日期
	private String createTimeTemp;	 //订单创建日期
	private Date payTime;		 //支付时间
	private Integer pid;		 //来源订单
	private String policyNumber; //保单号
	private String policyUrl; //保单下载地址
	private String cancelReason; //取消原因
	private String refundTime; //退款时间
	private String userInfoName; //用户名称
	private String isurantMessage;//被保险人公司是否错误信息
	private String insureMessage;//投保人公司是否错误信息
	
	public Integer getTakeEffect() {
		return takeEffect;
	}

	public void setTakeEffect(Integer takeEffect) {
		this.takeEffect = takeEffect;
	}

	public String getUserInfoName() {
		return userInfoName;
	}

	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
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

	public Integer getId() {
		return id;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName == "" ? null : insuranceName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate =beginDate == "" ? null : beginDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime == "" ? null : beginTime;
	}
	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate == "" ? null : endDate;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount ;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == "" ? null : endTime;
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

	public Date getCreateTime() throws ParseException {
		if(StringUtils.isNotBlank(this.createTimeTemp)){
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
			return dfs.parse(this.createTimeTemp);
		}
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds == "" ? null : employeeIds;
	}

	public Insure getInsure() {
		return insure;
	}

	public void setInsure(Insure insure) {
		this.insure = insure;
	}

	public Isurant getIsurant() {
		return isurant;
	}

	public void setIsurant(Isurant isurant) {
		this.isurant = isurant;
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

	public String getTransferAccountName() {
		return transferAccountName;
	}

	public void setTransferAccountName(String transferAccountName) {
		this.transferAccountName = transferAccountName;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	

	public String getPolicyUrl() {
		return policyUrl;
	}

	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}

	@Override
	public OrderDto clone() throws CloneNotSupportedException {
		return (OrderDto)super.clone();
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getCreateTimeTemp() {
		return createTimeTemp;
	}

	public void setCreateTimeTemp(String createTimeTemp) {
		this.createTimeTemp = createTimeTemp;
	}
	
}
