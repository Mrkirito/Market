package com.hangjia.bxj.model.log;

import com.hangjia.bxj.common.BaseCommonDO;

/**
 * @author yaoy
 * @since 2016-06-22
 */
public class SysLogDO extends BaseCommonDO {
	private Long id;
	private String operationName;
	private String operationMethod;
	private String operationRemark;
	private String operationIp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationMethod() {
		return operationMethod;
	}
	public void setOperationMethod(String operationMethod) {
		this.operationMethod = operationMethod;
	}
	public String getOperationRemark() {
		return operationRemark;
	}
	public void setOperationRemark(String operationRemark) {
		this.operationRemark = operationRemark;
	}
	public String getOperationIp() {
		return operationIp;
	}
	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
	}
	
}
