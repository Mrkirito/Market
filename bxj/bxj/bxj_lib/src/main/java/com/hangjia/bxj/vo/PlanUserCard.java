package com.hangjia.bxj.vo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PlanUserCard implements Serializable {
	private Integer fid;

	private String photo;

	private String name;

	private Integer sex;

	private String company;

	private String companyCode;

	private String area;

	private String areaCode;

	private String cities;

	private String citiesCode;

	private String job;

	private String jobCode;

	private String phone;

	private String department;

	private String address;

	private Date ctime;

	private String certificate;

	private Date uptime;

	private Integer certstate;

	private String jobyeartext;

	private Integer model;

	private String mark;

	private String qrcode;

	/**
	 * 实名认证状态
	 */
	private Boolean realCertState;

	/** 请求参数 **/
	private MultipartFile userImgPhoto;
	private MultipartFile userImg;

	/** 响应参数 **/
	private List<PlanUserImgVo> userImgs;
	private String shareUrl;

	private static final long serialVersionUID = 1L;

	public PlanUserCard() {
		super();
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo == null ? null : photo.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode == null ? null : areaCode.trim();
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job == null ? null : job.trim();
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode == null ? null : jobCode.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate == null ? null : certificate.trim();
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public Integer getCertstate() {
		return certstate;
	}

	public void setCertstate(Integer certstate) {
		this.certstate = certstate;
	}

	public String getJobyeartext() {
		return jobyeartext;
	}

	public void setJobyeartext(String jobyeartext) {
		this.jobyeartext = jobyeartext == null ? null : jobyeartext.trim();
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark == null ? null : mark.trim();
	}

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public String getCitiesCode() {
		return citiesCode;
	}

	public void setCitiesCode(String citiesCode) {
		this.citiesCode = citiesCode;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public MultipartFile getUserImg() {
		return userImg;
	}

	public void setUserImg(MultipartFile userImg) {
		this.userImg = userImg;
	}

	public MultipartFile getUserImgPhoto() {
		return userImgPhoto;
	}

	public void setUserImgPhoto(MultipartFile userImgPhoto) {
		this.userImgPhoto = userImgPhoto;
	}

	public List<PlanUserImgVo> getUserImgs() {
		return userImgs;
	}

	public void setUserImgs(List<PlanUserImgVo> userImgs) {
		this.userImgs = userImgs;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public Boolean getRealCertState() {
		return realCertState;
	}

	public void setRealCertState(Boolean realCertState) {
		this.realCertState = realCertState;
	}
}