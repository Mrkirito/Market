package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class NewPeopleCourse implements Serializable {
    private Long id;

    private Long userId;

    private Integer day;

    private Integer playCount;

    private Boolean isFalseCount;

    private Integer falseCount;

    private String lecturerName;

    private String title;

    private String courseImageUrl;

    private String courseUrl;
    
    private Date createTime;
    private Date updateTime;

    private Integer status;
    
    /**
	 * 课件详情ppt图片个数
	 */
	private Integer coursePPTNum;

    private static final long serialVersionUID = 1L;

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

	public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
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

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName == null ? null : lecturerName.trim();
    }

    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCourseImageUrl() {
        return courseImageUrl;
    }

    public void setCourseImageUrl(String courseImageUrl) {
        this.courseImageUrl = courseImageUrl == null ? null : courseImageUrl.trim();
    }

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", day=").append(day);
        sb.append(", playCount=").append(playCount);
        sb.append(", isFalseCount=").append(isFalseCount);
        sb.append(", falseCount=").append(falseCount);
        sb.append(", lecturerName=").append(lecturerName);
        sb.append(", title=").append(title);
        sb.append(", courseImageUrl=").append(courseImageUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}