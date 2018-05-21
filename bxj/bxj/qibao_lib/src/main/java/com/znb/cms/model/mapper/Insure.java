package com.znb.cms.model.mapper;

import java.io.Serializable;

public class Insure implements Serializable {
    private Integer id;

    private String companyName;//公司名称

    private String institCode;//统一社会信用代码

    private String license;

    private String receiptType;		//发票类型 0无需发票 1增值税普通发票(电子发票) 2增值税普通发票(纸质发票) 3增值税专用发票(纸质发票)

    private String linkman;//联系人

    private String telephone;//联系电话

    private String province;//省

    private String city;//市

    private String area;//县

    private Integer userId;//用户id


	private String taxRegNumber; 	//税务登记号
	private String taxRegAddress;	//税务登地址
	private String taxRegTel;		//税务登记电话
	private String taxBankName;		//税务开户银行名称
	private String taxBankAccount;	//税务开户银行账号

    private String mailingAddress;//地址

    private String email;//电子邮件

    private String imgUrl;//营业执照图片

    private String legalPerson;//法人

    private String message;

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

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType == null ? null : receiptType.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTaxRegNumber() {
        return taxRegNumber;
    }

    public void setTaxRegNumber(String taxRegNumber) {
        this.taxRegNumber = taxRegNumber == null ? null : taxRegNumber.trim();
    }

    public String getTaxRegAddress() {
        return taxRegAddress;
    }

    public void setTaxRegAddress(String taxRegAddress) {
        this.taxRegAddress = taxRegAddress == null ? null : taxRegAddress.trim();
    }

    public String getTaxRegTel() {
        return taxRegTel;
    }

    public void setTaxRegTel(String taxRegTel) {
        this.taxRegTel = taxRegTel == null ? null : taxRegTel.trim();
    }

    public String getTaxBankAccount() {
        return taxBankAccount;
    }

    public void setTaxBankAccount(String taxBankAccount) {
        this.taxBankAccount = taxBankAccount == null ? null : taxBankAccount.trim();
    }

    public String getTaxBankName() {
        return taxBankName;
    }

    public void setTaxBankName(String taxBankName) {
        this.taxBankName = taxBankName == null ? null : taxBankName.trim();
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress == null ? null : mailingAddress.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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