package com.hangjia.bxj.model.champion;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonDO;

public class VideoReportDO extends BaseCommonDO {
	private Integer reportId;
	private Date createTime;
	private Long userId;
	private String name;
	private String phone;
	private Integer reportCount;
	private Integer reportUserCount;
	private String content;
	private String title;
	private Long videoId;
	private Long fid;
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
	public Integer getReportUserCount() {
		return reportUserCount;
	}
	public void setReportUserCount(Integer reportUserCount) {
		this.reportUserCount = reportUserCount;
	}
}