package com.hangjia.bxj.model;

import java.io.Serializable;

/**
 * 赠险。
 * @author K9999
 *
 */
public class FreeInsurance implements Serializable {

	/**
	 * 赠险产品ID。
	 */
	private String id;
	
	/**
	 * 产品名称。
	 */
	private String name;
	
	private String shareTitle;
	
	private String shareDesc;
	
	/**
	 * 状态，为空或0表示正常（产品可使用），其他状态为不可使用（不可投保）状态。
	 */
	private Integer status;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareDesc() {
		return shareDesc;
	}

	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}

	/**
	 * 返回产品是否可使用。
	 * @return 可使用返回 {@code true}，其他返回 {@code false}。
	 */
	public boolean isHeal() {
		return status == null || status == 0;
	}

	private static final long serialVersionUID = -4124305464428419012L;

}
