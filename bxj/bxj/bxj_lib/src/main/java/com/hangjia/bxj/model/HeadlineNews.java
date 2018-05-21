package com.hangjia.bxj.model;

import java.util.Date;

public class HeadlineNews {
    private Long id;

    private String title;

    private String imgUrl;

    private String pageUrl;

    private Integer auditStatus;

    private Date onlineTime;

    private Date offlineTime;

    private Integer isDisplayTag;

    private String tagContent;

    private Long hitCount;

    private Long virtualHitCount;

    private Integer isDisplayReprint;

    private String reprintContent;

    private Integer newsType;

    private Integer sort;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String newsContent;

    private String fileUrl;
    
    private String timeContext; // 时间文案
    
    private Long totalHitCount; // hitCount + virtualHitCount
    
    private Integer status; // 状态 1有效 0无效
    public String getFileUrl() {
		return fileUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Long getId() {
        return id;
    }

    public String getTimeContext() {
		return timeContext;
	}

	public void setTimeContext(String timeContext) {
		this.timeContext = timeContext;
	}

	public Long getTotalHitCount() {
		
		return (this.hitCount == null ? 0L : this.hitCount) + (this.virtualHitCount == null ? 0L : this.virtualHitCount);
	}

	public void setTotalHitCount(Long totalHitCount) {
		this.totalHitCount = totalHitCount;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent == null ? null : tagContent.trim();
    }

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }

    public Long getVirtualHitCount() {
        return virtualHitCount;
    }

    public void setVirtualHitCount(Long virtualHitCount) {
        this.virtualHitCount = virtualHitCount;
    }

    public Integer getIsDisplayTag() {
		return isDisplayTag;
	}

	public void setIsDisplayTag(Integer isDisplayTag) {
		this.isDisplayTag = isDisplayTag;
	}

	public Integer getIsDisplayReprint() {
		return isDisplayReprint;
	}

	public void setIsDisplayReprint(Integer isDisplayReprint) {
		this.isDisplayReprint = isDisplayReprint;
	}

	public String getReprintContent() {
        return reprintContent;
    }

    public void setReprintContent(String reprintContent) {
        this.reprintContent = reprintContent == null ? null : reprintContent.trim();
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }
}