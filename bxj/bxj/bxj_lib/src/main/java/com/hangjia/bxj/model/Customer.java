package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户，
 * 即用户的保险推销对象。
 * @author K9999
 *
 */
public class Customer implements Serializable, DateFormatProvider {

	/**
	 * 唯一标识，
	 */
	private Long id;
	
	/**
	 * 所属用户，标识此客户是谁的客户。
	 */
	private Integer userId;
	
	/**
	 * 名称，称呼。
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 图片地址链接，如果用户没有上传头像，则为空。
	 */
	private String imageUrl;
	
	/**
	 * 联系手机号。
	 */
	private String mobile;
	
	/**
	 * 联系地址。
	 */
	private String address;

	/**
	 * 用户备注，由用户填写并显示给用户，帮助用户标识这个客户
	 */
	private String remark;
	
	/**
	 * 生日，确定用户年龄。
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	/**
	 * 电子邮件。
	 */
	private String email;
	
	/**
	 * 身份证号。
	 */
	private String idCardCode;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getBirthdayCN() {
		return birthday == null ? null : DATE_FORMAT_CN.format(birthday);
	}
	
	public String getBirthdayEN() {
		return birthday == null ? null : DATE_FORMAT.format(birthday);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCardCode() {
		return idCardCode;
	}

	public void setIdCardCode(String idCardCode) {
		this.idCardCode = idCardCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", address=");
		builder.append(address);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", birthday=");
		builder.append(getBirthdayCN());
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

	private static final long serialVersionUID = -7867004839340489684L;
	
}
