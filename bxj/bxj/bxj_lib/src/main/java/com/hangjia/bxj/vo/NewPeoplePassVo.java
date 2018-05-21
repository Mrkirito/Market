package com.hangjia.bxj.vo;

import java.util.Date;

public class NewPeoplePassVo {
	private Long id;

	private Long userId;
	/**
	 * 视频id
	 */
	private Long videoId;
	
	/**
	 * 新人通内容 
	 */
	private String newText;
	/**
	 * 第几天
	 */
	private Integer day;
	/**
	 * 认识保险
	 */
	private String knowExp;
	/**
	 * 视频列表
	 */
	private String videoName;
	/**
	 * 课件列表
	 */
	private String courseName;

	private Date createTime;
	
	private Date updateTime;

	private Integer status;
	
	/**
	 * 课件详情ppt图片个数
	 */
	private Integer coursePPTNum;

	/**
	 * 视频列表属性 如下：
	 */
    
	/**
	 * 视频链接
	 */
	private String pageUrl;
	/**
	 * 图片地址
	 */
	private String coverImageUrl;
	/**
	 * 讲师名称
	 */
	private String lecturerName;
	/**
	 * 播放时长
	 */
	private String durationTime;

	/**
	 * 课件属性 如下：
	 * 
	 * @return
	 */
	private String courseImageUrl;

	private Boolean isFalseCount;

	private Integer falseCount;
	/**
	 * 课件跳转路径 
	 */
	 private String courseUrl;
	/**
	 * 播放次数
	 */
	private Integer playCount;
	/**
	 * 课件 视频 名称
	 */
	private String title;
	
	 /**浏览次数**/
    private Integer browseNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public Integer getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}

	public String getCourseUrl() {
		return courseUrl;
	}

	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}
	
	public Integer getCoursePPTNum() {
		return coursePPTNum;
	}

	public void setCoursePPTNum(Integer coursePPTNum) {
		this.coursePPTNum = coursePPTNum;
	}

	public String getNewText() {
		return newText;
	}

	public void setNewText(String newText) {
		this.newText = newText;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getKnowExp() {
		return knowExp;
	}

	public void setKnowExp(String knowExp) {
		this.knowExp = knowExp == null ? null : knowExp.trim();
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName == null ? null : videoName.trim();
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public String getDurationTime() {
		return durationTime;
	}

	public Boolean getIsFalseCount() {
		return isFalseCount;
	}

	public void setIsFalseCount(Boolean isFalseCount) {
		this.isFalseCount = isFalseCount;
	}

	public Integer getFalseCount() {
		return falseCount;
	}

	public void setFalseCount(Integer falseCount) {
		this.falseCount = falseCount;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public String getCourseImageUrl() {
		return courseImageUrl;
	}

	public void setCourseImageUrl(String courseImageUrl) {
		this.courseImageUrl = courseImageUrl;
	}

}