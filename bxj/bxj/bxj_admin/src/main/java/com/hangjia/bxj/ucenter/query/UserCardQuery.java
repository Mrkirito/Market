package com.hangjia.bxj.ucenter.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * @author yaoy
 * @since 2016-07-12
 */
public class UserCardQuery extends BaseCommonQuery {
	
	private String phone;
	
	private String realName;
	
	private String idCard;

	private String startTime;
	
	private String endTime;
	
	private Integer realAuditStatus;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Integer getRealAuditStatus() {
		return realAuditStatus;
	}

	public void setRealAuditStatus(Integer realAuditStatus) {
		this.realAuditStatus = realAuditStatus;
	}
	
}
