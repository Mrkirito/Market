package com.hangjia.bxj.model.champion;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonDO;

public class VideoCommentDO extends BaseCommonDO {
	private Long fid;
	private Long videoId;
	private Long userId;
	private Integer auditStatus;
	private Date auditAt;
	private Integer clickLikeCount;
	private String comment;
	private Date createAt;
	private String nickName;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
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
	public Date getAuditAt() {
		return auditAt;
	}
	public void setAuditAt(Date auditAt) {
		this.auditAt = auditAt;
	}
	public Integer getClickLikeCount() {
		return clickLikeCount;
	}
	public void setClickLikeCount(Integer clickLikeCount) {
		this.clickLikeCount = clickLikeCount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}