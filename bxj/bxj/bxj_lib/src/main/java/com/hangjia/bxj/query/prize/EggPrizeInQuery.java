package com.hangjia.bxj.query.prize;

import com.hangjia.bxj.common.BaseCommonQuery;


/**
 * @author yaoy
 * @since 2016-06-17
 */
public class EggPrizeInQuery extends BaseCommonQuery {

	private String startTime;
	
	private String endTime;
	
	private String name;
	
	private Integer status;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
