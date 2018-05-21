package com.hangjia.bxj.model;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

public class AppExtendData extends BaseCommonQuery {
	 private Long id;

	    private Integer channelNum;

	    private Integer everyNum;

	    private Integer classroomNum;

	    private Integer lineNum;

	    private Integer activityNum;

	    private Integer totalNum;

	    private Date dataTime;

	    private Integer status;

	    private String createName;

	    private Date createTime;

	    private String updateName;

	    private Date updateTime;
	    
	    
	    
	    private String startTime;
	    private String endTime;
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Integer getChannelNum() {
	        return channelNum;
	    }

	    public void setChannelNum(Integer channelNum) {
	        this.channelNum = channelNum;
	    }

	    public Integer getEveryNum() {
	        return everyNum;
	    }

	    public void setEveryNum(Integer everyNum) {
	        this.everyNum = everyNum;
	    }

	    public Integer getClassroomNum() {
	        return classroomNum;
	    }

	    public void setClassroomNum(Integer classroomNum) {
	        this.classroomNum = classroomNum;
	    }

	    public Integer getLineNum() {
	        return lineNum;
	    }

	    public void setLineNum(Integer lineNum) {
	        this.lineNum = lineNum;
	    }

	    public Integer getActivityNum() {
	        return activityNum;
	    }

	    public void setActivityNum(Integer activityNum) {
	        this.activityNum = activityNum;
	    }

	    public Integer getTotalNum() {
	        return totalNum;
	    }

	    public void setTotalNum(Integer totalNum) {
	        this.totalNum = totalNum;
	    }

	    public Date getDataTime() {
	        return dataTime;
	    }

	    public void setDataTime(Date dataTime) {
	        this.dataTime = dataTime;
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public String getCreateName() {
	        return createName;
	    }

	    public void setCreateName(String createName) {
	        this.createName = createName == null ? null : createName.trim();
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public String getUpdateName() {
	        return updateName;
	    }

	    public void setUpdateName(String updateName) {
	        this.updateName = updateName == null ? null : updateName.trim();
	    }

	    public Date getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(Date updateTime) {
	        this.updateTime = updateTime;
	    }

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

	

	    
}