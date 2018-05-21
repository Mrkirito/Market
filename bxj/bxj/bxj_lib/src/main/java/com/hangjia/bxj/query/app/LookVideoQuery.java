package com.hangjia.bxj.query.app;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * 观看视频统计
 */
public class LookVideoQuery extends BaseCommonQuery {
	
	private Date lookStartTime;
	
	private Date lookEndTime;
	
	private String mobile;

	private Long userId;
	
	private Date playTime;
	
	private String title;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	public Date getLookStartTime() {
		return lookStartTime;
	}

	public void setLookStartTime(Date lookStartTime) {
		this.lookStartTime = lookStartTime;
	}

	public Date getLookEndTime() {
		return lookEndTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLookEndTime(Date lookEndTime) {
		this.lookEndTime = lookEndTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
