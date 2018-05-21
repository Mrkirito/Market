package com.hangjia.bxj.query.log;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

/** 
* @author  作者 : yaoy
* @date 2016年6月24日 下午2:32:53 
* @version 1.0 
*/
public class SysLogQuery extends BaseCommonQuery {
	private Date createTime;

	private String operationName;
	
	private String operationIp;
	
	private String operationMethod;
	
	private String operationRemark;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getOperationIp() {
		return operationIp;
	}

	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
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
	
}
