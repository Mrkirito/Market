package com.hangjia.bxj.query.champion;

import java.util.Date;
import java.util.List;

import com.hangjia.bxj.common.BaseCommonQuery;

/** 
* @author  作者 : yaoy
* @date 2016年7月13日 下午2:32:53 
* @version 1.0 
*/
public class VideoReportQuery extends BaseCommonQuery {
	private String title;
	private Date startTime;
	private Date endTime;
	private Long userId;
	private String name;
	private Integer dimension;
	private Integer reportId;
	private String phone;
	private List<Long> userIds;
	
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getDimension() {
		return dimension;
	}
	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}
	public Date getStartTime() {
		return startTime;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
