package com.hangjia.bxj.vo;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

public class VideoInviteCountVo extends BaseCommonQuery{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4770132855532848282L;

	/**
	 * 邀请人id
	 */
	private Long inviteUserId;
	
	/**
	 * 手机号 
	 */
	private String mobile;

	/**
	 * 邀请时间
	 */
	private Date inviteAt;

	/**
	 * 每个邀请人人数
	 */
	private Integer inviteNum;

	/**
	 * 邀请总人数
	 */
	private Integer inviteTotal;

	/**
	 * 视频标题
	 */
	private String videotitle;
	/**
	 * 视频id
	 */
	private Long videoId;

	/**
	 * 视频用券 总人数
	 */
	private Integer videVocherTotal;
	/**
	 * 视频 每个用券人数
	 */
	private Integer videVocherNum;
	
	/**
	 * 创建时间
	 */
	private Date createAt;
	
	public Long getInviteUserId() {
		return inviteUserId;
	}
	public void setInviteUserId(Long inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getInviteAt() {
		return inviteAt;
	}
	public void setInviteAt(Date inviteAt) {
		this.inviteAt = inviteAt;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Integer getInviteNum() {
		return inviteNum;
	}
	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}
	public Integer getInviteTotal() {
		return inviteTotal;
	}
	public void setInviteTotal(Integer inviteTotal) {
		this.inviteTotal = inviteTotal;
	}
	public String getVideotitle() {
		return videotitle;
	}
	public void setVideotitle(String videotitle) {
		this.videotitle = videotitle;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public Integer getVideVocherTotal() {
		return videVocherTotal;
	}
	public void setVideVocherTotal(Integer videVocherTotal) {
		this.videVocherTotal = videVocherTotal;
	}
	public Integer getVideVocherNum() {
		return videVocherNum;
	}
	public void setVideVocherNum(Integer videVocherNum) {
		this.videVocherNum = videVocherNum;
	}
	

}