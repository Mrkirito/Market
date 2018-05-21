package com.hangjia.bxj.query.prize;

import java.util.List;

import com.hangjia.bxj.common.BaseCommonQuery;


/**
 * @author yaoy
 * @since 2016-06-17
 */
public class EggPrizeQuery extends BaseCommonQuery {
	
	private String startTime;
	private String endTime;
	private String name;
	private Integer isEnable;
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
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
}
