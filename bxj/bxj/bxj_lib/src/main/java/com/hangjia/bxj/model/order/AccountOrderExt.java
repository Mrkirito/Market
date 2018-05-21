package com.hangjia.bxj.model.order;

import java.io.Serializable;

public class AccountOrderExt implements Serializable{
	private String orderId;
	private String videoName;
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}