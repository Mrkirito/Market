package com.hangjia.bxj.vo;

import java.io.Serializable;
import java.util.Date;

public class LookVideoDetailVo implements Serializable{
	private Long fid;
	
	private String title;
	
	private Date playTime;
	
	private Integer isVoucher;

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	public Integer getIsVoucher() {
		return isVoucher;
	}

	public void setIsVoucher(Integer isVoucher) {
		this.isVoucher = isVoucher;
	}
	
}