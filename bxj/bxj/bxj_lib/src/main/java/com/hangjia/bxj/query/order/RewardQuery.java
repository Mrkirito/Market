package com.hangjia.bxj.query.order;

import java.util.List;

import com.hangjia.bxj.common.BaseCommonQuery;


/**
 * @author yaoy
 * @since 2016-06-17
 */
public class RewardQuery extends BaseCommonQuery {
	private Long userId;
	private String userName;
	private String nickName;
	private String videoName;
	private String orderId;
	private String startTime;
	private String endTime;
	private Integer dimension;
	private List<Long> userIds;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getDimension() {
		return dimension;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setDimension(Integer dimension) {
		this.dimension = dimension;
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
