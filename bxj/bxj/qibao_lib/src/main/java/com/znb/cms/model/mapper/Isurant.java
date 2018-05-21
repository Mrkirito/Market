package com.znb.cms.model.mapper;

import java.io.Serializable;


public class Isurant implements Serializable {
    private Integer id;

	private String companyName;	//企业名称
	private String license;	//
	private String institCode; 	//机构代码
	private String imgUrl;		//执照图片路径 
	private Integer userId;	//当前用户id
	private String legalPerson;			//法人
	private String message;			//错误信息

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == "" ? null : companyName.trim();
    }

    public String getInstitCode() {
        return institCode;
    }

    public void setInstitCode(String institCode) {
        this.institCode = institCode == null ? null : institCode.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}