package com.hangjia.bxj.common;

import java.io.Serializable;
import java.util.Date;

/** 
* @author  作者 : yaoy
* @date 2016年5月3日 下午2:30:15 
* @version 1.0 
*/
public class BaseCommonDO implements Serializable {
	
	private Date createTime;
	
	private String createName;
	
	private Date updateTime;
	
	private String updateName;
	
	private Integer status;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
