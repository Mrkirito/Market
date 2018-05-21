package com.hangjia.bxj.vo;

import java.io.Serializable;
import java.util.Date;

public class LookVideoVo implements Serializable{
	private Long userId;
	
	private Integer lookNum;
	
	private Integer vocherNum;
	
	private String mobile;
	
	private Date lookTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getLookNum() {
		return lookNum;
	}

	public void setLookNum(Integer lookNum) {
		this.lookNum = lookNum;
	}

	public Integer getVocherNum() {
		return vocherNum;
	}

	public void setVocherNum(Integer vocherNum) {
		this.vocherNum = vocherNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getLookTime() {
		return lookTime;
	}

	public void setLookTime(Date lookTime) {
		this.lookTime = lookTime;
	}
	
}