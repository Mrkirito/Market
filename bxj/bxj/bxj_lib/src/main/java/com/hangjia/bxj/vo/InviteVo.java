package com.hangjia.bxj.vo;

import java.io.Serializable;
import java.util.Date;

public class InviteVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;	
	private Date date;
	private Integer count;
	private String dateStr;
	private Integer count1=0;
	private Integer count2=0;
	private Integer count3=0;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCount1() {
		return count1;
	}
	public void setCount1(Integer count1) {
		this.count1 = count1;
	}
	public Integer getCount2() {
		return count2;
	}
	public void setCount2(Integer count2) {
		this.count2 = count2;
	}
	public Integer getCount3() {
		return count3;
	}
	public void setCount3(Integer count3) {
		this.count3 = count3;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
}
