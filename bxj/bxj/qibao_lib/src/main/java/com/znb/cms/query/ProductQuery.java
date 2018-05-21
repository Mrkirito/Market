package com.znb.cms.query;

import com.znb.cms.common.BaseCommonQuery;

public class ProductQuery extends BaseCommonQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -489050751782028283L;
	private Integer id;
	
	private String name;

	private Integer productTypeId;

	private Integer companyId;

	private Integer isOn;

	private Integer peopleGroupId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIsOn() {
		return isOn;
	}

	public void setIsOn(Integer isOn) {
		this.isOn = isOn;
	}

	public Integer getPeopleGroupId() {
		return peopleGroupId;
	}

	public void setPeopleGroupId(Integer peopleGroupId) {
		this.peopleGroupId = peopleGroupId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
