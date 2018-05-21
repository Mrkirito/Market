package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 赠险（免费险）领取记录
 * @author K9999
 *
 */
public class FreeReceiveLog implements Serializable {

	private Long id;
	
	private String name;
	
	private String freeId;
	
	private Long customerId;
	
	private Date createAt;
	
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getCreateAtCN() {
		if (createAt != null) {
			return DateFormatProvider.DATE_FORMAT_CN.format(createAt);
		}
		return null;
	}
	
	public boolean isReceived() {
		if (status != null && status == 1) {
			return true;
		}
		return false;
	}
	
	public void setReceived(boolean received) {
		if (received) {
			this.status = 1;
		} else {
			this.status = 0;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final long serialVersionUID = -6638788794774370886L;

}
