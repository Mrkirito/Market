package com.hangjia.bxj.query.champion;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

/** 
* @author  作者 : yaoy
* @date 2016年5月3日 下午2:32:53 
* @version 1.0 
*/
public class ChampionVideoQuery extends BaseCommonQuery {

	private Long fid; // id
	private String likeName; // 标题
	private Long channelId; // 频道id
	private Integer auditStatus; // 审核状态
	private Integer videoType; // 文件类型
	private Integer playType; // 是否独播
	private Integer isRecommend; // 是否推荐
	private Integer voucherType; // 是否用券
	private Date uploadTime; // 上传时间
	private String lecturerName; // 讲师
	private Integer falseCountType;
	private Integer falseCount;
	public Integer getFalseCountType() {
		return falseCountType;
	}
	public void setFalseCountType(Integer falseCountType) {
		this.falseCountType = falseCountType;
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
		this.lecturerName = lecturerName;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public Long getFid() {
		return fid;
	}
	public String getLikeName() {
		return likeName;
	}
	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getVideoType() {
		return videoType;
	}
	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}
	public Integer getPlayType() {
		return playType;
	}
	public void setPlayType(Integer playType) {
		this.playType = playType;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(Integer voucherType) {
		this.voucherType = voucherType;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
}
