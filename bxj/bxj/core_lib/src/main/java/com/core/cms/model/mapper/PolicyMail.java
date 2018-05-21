package com.core.cms.model.mapper;

/**
 * 保单mail信息
* @author yuanxin
* @date 2017年5月26日上午10:09:59
* @version <b>1.0.0</b>
 */
public class PolicyMail {
	
	private byte[] bytePolicy;//保单byte数组
	
	private String mailPolicy ;//保单mail发送地址
	
	private String userName;//被保人姓名
	
	private String productName; //产品名称

	public byte[] getBytePolicy() {
		return bytePolicy;
	}

	public void setBytePolicy(byte[] bytePolicy) {
		this.bytePolicy = bytePolicy;
	}

	public String getMailPolicy() {
		return mailPolicy;
	}

	public void setMailPolicy(String mailPolicy) {
		this.mailPolicy = mailPolicy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
}
