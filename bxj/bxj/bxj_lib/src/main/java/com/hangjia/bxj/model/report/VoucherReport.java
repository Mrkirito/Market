package com.hangjia.bxj.model.report;

import java.util.Date;

public class VoucherReport {
	
	private Integer sendSum; // 发券数
	private Integer sendUserCount; // 发券的人数
	private Integer useSum; // 用券数
	private Integer useUserCount; // 用券的人数
	private Integer haveUserCount; // 有券的人数
	private Date createAt; // 时间
	
	public Integer getSendSum() {
		return sendSum;
	}
	public void setSendSum(Integer sendSum) {
		this.sendSum = sendSum;
	}
	public Integer getSendUserCount() {
		return sendUserCount;
	}
	public void setSendUserCount(Integer sendUserCount) {
		this.sendUserCount = sendUserCount;
	}
	public Integer getUseSum() {
		return useSum;
	}
	public void setUseSum(Integer useSum) {
		this.useSum = useSum;
	}
	public Integer getUseUserCount() {
		return useUserCount;
	}
	public void setUseUserCount(Integer useUserCount) {
		this.useUserCount = useUserCount;
	}
	public Integer getHaveUserCount() {
		return haveUserCount;
	}
	public void setHaveUserCount(Integer haveUserCount) {
		this.haveUserCount = haveUserCount;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
}