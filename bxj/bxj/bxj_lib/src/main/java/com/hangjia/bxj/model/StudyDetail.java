package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StudyDetail implements Serializable {
    private Long id;

    private Integer knowID;
    
    private String title;

    private String imgUrl;

    private String pageUrl;

    private Integer auditStatus;

    private Integer  status ; 
  
    private Date onlineTime;

    private Date offlineTime;

    private Integer isFalse;

    private Integer isDisplayTag;

    private String tagContent;

    private Long hitCount;

    private Long virtualHitCount;

    private Integer isDisplayReprint;

    private String reprintContent;

    private Integer studyType;

    private Integer sort;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String studyContent;
    
    private String fileUrl;
    
    private String timeContext; // 时间文案
    
    private Integer studyTypeID; //类型id
    
    private String typeName; //类型名称
    /**类型集合**/
    List<StudyType> typeResults;
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<StudyType> getTypeResults() {
		return typeResults;
	}

	public void setTypeResults(List<StudyType> typeResults) {
		this.typeResults = typeResults;
	}

	public Integer getKnowID() {
		return knowID;
	}

	public void setKnowID(Integer knowID) {
		this.knowID = knowID;
	}

	public Integer getStudyTypeID() {
		return studyTypeID;
	}

	public void setStudyTypeID(Integer studyTypeID) {
		this.studyTypeID = studyTypeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getTimeContext() {
		return timeContext;
	}

	public void setTimeContext(String timeContext) {
		this.timeContext = timeContext;
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

    public Integer getIsFalse() {
		return isFalse;
	}

	public void setIsFalse(Integer isFalse) {
		this.isFalse = isFalse;
	}

	public Integer getIsDisplayTag() {
		return isDisplayTag;
	}

	public void setIsDisplayTag(Integer isDisplayTag) {
		this.isDisplayTag = isDisplayTag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIsDisplayReprint(Integer isDisplayReprint) {
		this.isDisplayReprint = isDisplayReprint;
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

    
    public Integer getIsDisplayReprint() {
		return isDisplayReprint;
	}

	public String getReprintContent() {
        return reprintContent;
    }

    public void setReprintContent(String reprintContent) {
        this.reprintContent = reprintContent == null ? null : reprintContent.trim();
    }

    public Integer getStudyType() {
        return studyType;
    }

    public void setStudyType(Integer studyType) {
        this.studyType = studyType;
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

    public String getStudyContent() {
        return studyContent;
    }

    public void setStudyContent(String studyContent) {
        this.studyContent = studyContent == null ? null : studyContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", pageUrl=").append(pageUrl);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", onlineTime=").append(onlineTime);
        sb.append(", offlineTime=").append(offlineTime);
        sb.append(", isFalse=").append(isFalse);
        sb.append(", isDisplayTag=").append(isDisplayTag);
        sb.append(", tagContent=").append(tagContent);
        sb.append(", hitCount=").append(hitCount);
        sb.append(", virtualHitCount=").append(virtualHitCount);
        sb.append(", isDisplayReprint=").append(isDisplayReprint);
        sb.append(", reprintContent=").append(reprintContent);
        sb.append(", studyType=").append(studyType);
        sb.append(", sort=").append(sort);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", studyContent=").append(studyContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}