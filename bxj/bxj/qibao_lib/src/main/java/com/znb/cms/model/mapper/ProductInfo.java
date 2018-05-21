package com.znb.cms.model.mapper;

import java.io.Serializable;

public class ProductInfo implements Serializable{
    private Integer id;

    private String name;

    private Double totalStar;

    private Integer productTypeId;

    private Integer isHot;

    private Double hotSort;

    private String logo;

    private Double brandStar;

    private Double ensureStar;

    private Double financialStar;

    private String ensureContent;

    private String protectAgeDesc;

    private String protectAge;

    private String protectPeriodDesc;

    private String protectPeriod;

    private String paymentTypeDesc;

    private String paymentType;

    private Integer companyId;

    private Integer isOn;

    private Integer peopleGroupId;

    private String shortName;

    private String payUrl;

    private String payChannel;

    private Integer existRate;
    
    private String companyName;
    private String peopleGroupName;
    private String productTypeName;
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

    public Double getTotalStar() {
        return totalStar;
    }

    public void setTotalStar(Double totalStar) {
        this.totalStar = totalStar;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Double getHotSort() {
        return hotSort;
    }

    public void setHotSort(Double hotSort) {
        this.hotSort = hotSort;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Double getBrandStar() {
        return brandStar;
    }

    public void setBrandStar(Double brandStar) {
        this.brandStar = brandStar;
    }

    public Double getEnsureStar() {
        return ensureStar;
    }

    public void setEnsureStar(Double ensureStar) {
        this.ensureStar = ensureStar;
    }

    public Double getFinancialStar() {
        return financialStar;
    }

    public void setFinancialStar(Double financialStar) {
        this.financialStar = financialStar;
    }

    public String getEnsureContent() {
        return ensureContent;
    }

    public void setEnsureContent(String ensureContent) {
        this.ensureContent = ensureContent == null ? null : ensureContent.trim();
    }

    public String getProtectAgeDesc() {
        return protectAgeDesc;
    }

    public void setProtectAgeDesc(String protectAgeDesc) {
        this.protectAgeDesc = protectAgeDesc == null ? null : protectAgeDesc.trim();
    }

    public String getProtectAge() {
        return protectAge;
    }

    public void setProtectAge(String protectAge) {
        this.protectAge = protectAge == null ? null : protectAge.trim();
    }

    public String getProtectPeriodDesc() {
        return protectPeriodDesc;
    }

    public void setProtectPeriodDesc(String protectPeriodDesc) {
        this.protectPeriodDesc = protectPeriodDesc == null ? null : protectPeriodDesc.trim();
    }

    public String getProtectPeriod() {
        return protectPeriod;
    }

    public void setProtectPeriod(String protectPeriod) {
        this.protectPeriod = protectPeriod == null ? null : protectPeriod.trim();
    }

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc == null ? null : paymentTypeDesc.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl == null ? null : payUrl.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPeopleGroupName() {
		return peopleGroupName;
	}

	public void setPeopleGroupName(String peopleGroupName) {
		this.peopleGroupName = peopleGroupName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Integer getExistRate() {
		return existRate;
	}

	public void setExistRate(Integer existRate) {
		this.existRate = existRate;
	}
    
}