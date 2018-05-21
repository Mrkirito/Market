package com.hangjia.bxj.ucenter.model;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonDO;

public class UserCard extends BaseCommonDO{
	
	private Long fid;
	
	private String phone;
	
	private Date auditTime;
	
	private String realName;
	
	private String idCard;
	
	private String idCardFrontUrl;
	
	private String idCardReverseUrl;
	
	private Integer realAuditStatus;
	
	private String auditReason;
	
	private Date applyTime;
	
	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardFrontUrl() {
		return idCardFrontUrl;
	}

	public void setIdCardFrontUrl(String idCardFrontUrl) {
		this.idCardFrontUrl = idCardFrontUrl;
	}

	public String getIdCardReverseUrl() {
		return idCardReverseUrl;
	}

	public void setIdCardReverseUrl(String idCardReverseUrl) {
		this.idCardReverseUrl = idCardReverseUrl;
	}

	public Integer getRealAuditStatus() {
		return realAuditStatus;
	}

	public void setRealAuditStatus(Integer realAuditStatus) {
		this.realAuditStatus = realAuditStatus;
	}

	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
}