package com.hangjia.bxj.query.report;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

/** 
* @author  作者 : yaoy
* @date 2016年6月8日 下午2:32:53 
* @version 1.0 
*/
public class VoucherReportQuery extends BaseCommonQuery {
	
	private Date startTime; // 统计开始时间
	
	private Date endTime; // 统计结束时间
	
	private Integer dimension; // 维度

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}
	
}
