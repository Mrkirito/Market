package com.znb.cms.model.mapper;

import java.io.Serializable;
import java.util.Date;
/**
 * 产品
 * @author fhj
 * @date 2017年3月17日 下午2:27:30
 * @version <b>1.0.0</b>
 */
public class Insurance implements Serializable {
	private static final long serialVersionUID = -5454884136060306719L;
	
	private Integer id;
	private String name; // 保险名称
	private String logo; // 保险产品logo
	private String featureDesc; // 特征描述
	private String companyName; // 企业名称
	private String ageDesc; // 年龄描述
	private Date createTime; // 创建时间
	private Boolean isOn = true;// 是否上线(false-否 true-是)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getFeatureDesc() {
		return featureDesc;
	}

	public void setFeatureDesc(String featureDesc) {
		this.featureDesc = featureDesc == null ? null : featureDesc.trim();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public String getAgeDesc() {
		return ageDesc;
	}

	public void setAgeDesc(String ageDesc) {
		this.ageDesc = ageDesc == null ? null : ageDesc.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsOn() {
		return isOn;
	}

	public void setIsOn(Boolean isOn) {
		this.isOn = isOn;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo == null ? null : logo.trim();
	}
	
}