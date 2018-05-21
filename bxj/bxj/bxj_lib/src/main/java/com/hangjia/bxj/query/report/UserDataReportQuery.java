package com.hangjia.bxj.query.report;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

/** 
* @author  作者 : yaoy
* @date 2016年8月1日 下午2:32:53 
* @version 1.0 
*/
public class UserDataReportQuery extends BaseCommonQuery {
	
	private Date dataTime;

	private String startTime;
	
	private String endTime;
	
	private Integer echartsType;
	
	private Long id;
	
	public Integer getEchartsType() {
		return echartsType;
	}

	public void setEchartsType(Integer echartsType) {
		this.echartsType = echartsType;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
