package com.core.cms.model.mapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradeAssured implements Serializable {
    private Long fid;

    private Integer orderId;

    private String thirdOrderId;

    private String tradeId;

    private Integer flag;

    private String realName;

    private Integer sex;

    private Date birthday;

    private String identityId;

    private String identityName;

    private String identityCard;

    private Date identityStopDate;

    private Integer isSecurity;

    private BigDecimal yearEarnings;

    private String mobileCode;

    private String relatives;

    private String stateCode;

    private String cityCode;

    private String townCode;

    private String state;

    private String city;

    private String town;

    private String zipcode;

    private String address;

    private String mail;

    private String careerName;

    private String companyName;

    private String countryName;

    private String travelName;

    private String certificateCode1;

    private String certificateCode2;

    private String insureArea;

    private String flightNumber;

    private String schoolType;

    private String schoolNature;

    private String job1Code;

    private String job1Name;

    private String job2Code;

    private String job2Name;

    private String job3Code;

    private String job3Name;

    private String insureState;

    private String insureAddress;

    private String favoredSort;

    private Integer favoredSortProportion;

    private String post;

    private Integer isSpouse;

    private String xqName;

    private String xqBankCode;

    private String xqBankName;

    private String xqBankNo;

    private String bankStateId;

    private String bankStateName;

    private String bankCityId;

    private String bankCityName;

    private Date createTime;

    private Integer memberId;

    private String xqSubBankName;

    private String xqSubBankCode;

    private String height;

    private String weight;
    
    private static final long serialVersionUID = 1L;

    
    public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getThirdOrderId() {
        return thirdOrderId;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId == null ? null : thirdOrderId.trim();
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId == null ? null : identityId.trim();
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName == null ? null : identityName.trim();
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    public Date getIdentityStopDate() {
        return identityStopDate;
    }

    public void setIdentityStopDate(Date identityStopDate) {
        this.identityStopDate = identityStopDate;
    }

    public Integer getIsSecurity() {
        return isSecurity;
    }

    public void setIsSecurity(Integer isSecurity) {
        this.isSecurity = isSecurity;
    }

    public BigDecimal getYearEarnings() {
        return yearEarnings;
    }

    public void setYearEarnings(BigDecimal yearEarnings) {
        this.yearEarnings = yearEarnings;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode == null ? null : mobileCode.trim();
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives == null ? null : relatives.trim();
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode == null ? null : stateCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode == null ? null : townCode.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName == null ? null : careerName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName == null ? null : travelName.trim();
    }

    public String getCertificateCode1() {
        return certificateCode1;
    }

    public void setCertificateCode1(String certificateCode1) {
        this.certificateCode1 = certificateCode1 == null ? null : certificateCode1.trim();
    }

    public String getCertificateCode2() {
        return certificateCode2;
    }

    public void setCertificateCode2(String certificateCode2) {
        this.certificateCode2 = certificateCode2 == null ? null : certificateCode2.trim();
    }

    public String getInsureArea() {
        return insureArea;
    }

    public void setInsureArea(String insureArea) {
        this.insureArea = insureArea == null ? null : insureArea.trim();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber == null ? null : flightNumber.trim();
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType == null ? null : schoolType.trim();
    }

    public String getSchoolNature() {
        return schoolNature;
    }

    public void setSchoolNature(String schoolNature) {
        this.schoolNature = schoolNature == null ? null : schoolNature.trim();
    }

    public String getJob1Code() {
        return job1Code;
    }

    public void setJob1Code(String job1Code) {
        this.job1Code = job1Code == null ? null : job1Code.trim();
    }

    public String getJob1Name() {
        return job1Name;
    }

    public void setJob1Name(String job1Name) {
        this.job1Name = job1Name == null ? null : job1Name.trim();
    }

    public String getJob2Code() {
        return job2Code;
    }

    public void setJob2Code(String job2Code) {
        this.job2Code = job2Code == null ? null : job2Code.trim();
    }

    public String getJob2Name() {
        return job2Name;
    }

    public void setJob2Name(String job2Name) {
        this.job2Name = job2Name == null ? null : job2Name.trim();
    }

    public String getJob3Code() {
        return job3Code;
    }

    public void setJob3Code(String job3Code) {
        this.job3Code = job3Code == null ? null : job3Code.trim();
    }

    public String getJob3Name() {
        return job3Name;
    }

    public void setJob3Name(String job3Name) {
        this.job3Name = job3Name == null ? null : job3Name.trim();
    }

    public String getInsureState() {
        return insureState;
    }

    public void setInsureState(String insureState) {
        this.insureState = insureState == null ? null : insureState.trim();
    }

    public String getInsureAddress() {
        return insureAddress;
    }

    public void setInsureAddress(String insureAddress) {
        this.insureAddress = insureAddress == null ? null : insureAddress.trim();
    }

    public String getFavoredSort() {
        return favoredSort;
    }

    public void setFavoredSort(String favoredSort) {
        this.favoredSort = favoredSort == null ? null : favoredSort.trim();
    }

    public Integer getFavoredSortProportion() {
        return favoredSortProportion;
    }

    public void setFavoredSortProportion(Integer favoredSortProportion) {
        this.favoredSortProportion = favoredSortProportion;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Integer getIsSpouse() {
        return isSpouse;
    }

    public void setIsSpouse(Integer isSpouse) {
        this.isSpouse = isSpouse;
    }

    public String getXqName() {
        return xqName;
    }

    public void setXqName(String xqName) {
        this.xqName = xqName == null ? null : xqName.trim();
    }

    public String getXqBankCode() {
        return xqBankCode;
    }

    public void setXqBankCode(String xqBankCode) {
        this.xqBankCode = xqBankCode == null ? null : xqBankCode.trim();
    }

    public String getXqBankName() {
        return xqBankName;
    }

    public void setXqBankName(String xqBankName) {
        this.xqBankName = xqBankName == null ? null : xqBankName.trim();
    }

    public String getXqBankNo() {
        return xqBankNo;
    }

    public void setXqBankNo(String xqBankNo) {
        this.xqBankNo = xqBankNo == null ? null : xqBankNo.trim();
    }

    public String getBankStateId() {
        return bankStateId;
    }

    public void setBankStateId(String bankStateId) {
        this.bankStateId = bankStateId == null ? null : bankStateId.trim();
    }

    public String getBankStateName() {
        return bankStateName;
    }

    public void setBankStateName(String bankStateName) {
        this.bankStateName = bankStateName == null ? null : bankStateName.trim();
    }

    public String getBankCityId() {
        return bankCityId;
    }

    public void setBankCityId(String bankCityId) {
        this.bankCityId = bankCityId == null ? null : bankCityId.trim();
    }

    public String getBankCityName() {
        return bankCityName;
    }

    public void setBankCityName(String bankCityName) {
        this.bankCityName = bankCityName == null ? null : bankCityName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getXqSubBankName() {
        return xqSubBankName;
    }

    public void setXqSubBankName(String xqSubBankName) {
        this.xqSubBankName = xqSubBankName == null ? null : xqSubBankName.trim();
    }

    public String getXqSubBankCode() {
        return xqSubBankCode;
    }

    public void setXqSubBankCode(String xqSubBankCode) {
        this.xqSubBankCode = xqSubBankCode == null ? null : xqSubBankCode.trim();
    }
}