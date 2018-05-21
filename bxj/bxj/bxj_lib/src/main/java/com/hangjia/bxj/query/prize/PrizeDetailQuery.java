package com.hangjia.bxj.query.prize;


import com.hangjia.bxj.common.BaseCommonQuery;

 
public class PrizeDetailQuery extends BaseCommonQuery {
	
	private String startTime;
	private String endTime;
	private String name;
	private Integer logStatus; //领取状态
	private String takeName; 
	private String takephone;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}
	public String getTakeName() {
		return takeName;
	}
	public void setTakeName(String takeName) {
		this.takeName = takeName;
	}
	public String getTakephone() {
		return takephone;
	}
	public void setTakephone(String takephone) {
		this.takephone = takephone;
	}
	
   
}
