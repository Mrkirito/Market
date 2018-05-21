package com.hangjia.bxj.query.champion;

import com.hangjia.bxj.common.BaseCommonQuery;

/** 
* @author  作者 : yaoy
* @date 2016年6月27日 下午2:32:53 
* @version 1.0 
*/
public class VideoCommentQuery extends BaseCommonQuery {
	private String comment;
	private Long userId;
	private Long videoId;
	private Integer auditStatus;

	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
}
