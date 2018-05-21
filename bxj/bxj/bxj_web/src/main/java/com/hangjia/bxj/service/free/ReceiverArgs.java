package com.hangjia.bxj.service.free;

import java.io.Serializable;

/**
 * 领取赠险所需各项参数。
 * @author K9999
 *
 */
public class ReceiverArgs implements Serializable {

	/**
	 * 免费险产品ID，产品
	 */
	private String pid;
	
	/**
	 * 真实姓名。
	 */
	private String realName;
	
	/**
	 * 手机号。
	 */
	private String mobile;
	
	/**
	 * 身份证号。
	 */
	private String idCardCode;
	
	/**
	 * 用户来源，默认 102。
	 */
	private static final String sourceId = "102";
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCardCode() {
		return idCardCode;
	}

	public void setIdCardCode(String idCardCode) {
		this.idCardCode = idCardCode;
	}

	public String getSourceId() {
		return sourceId;
	}

	private static final long serialVersionUID = 4440385526858394449L;

}
