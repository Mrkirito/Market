package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 我邀请的用户。
 * @author K9999
 *
 */
public class MyJunior implements Serializable {

	/**
	 * 主键，无业务意义。
	 */
	private Long id;
	
	/**
	 * 我的用户ID。
	 */
	private Integer userId;
	
	/**
	 * 被邀请人注册的手机号。
	 */
	private String sharedMobile;
	
	/**
	 * 被邀请人注册时间。
	 */
	private Date createAt;
	
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

	public String getSharedMobile() {
		return sharedMobile;
	}

	public void setSharedMobile(String sharedMobile) {
		this.sharedMobile = sharedMobile;
	}

	public Date getCreateAt() {
		return createAt;
	}
	
	public String getCreateAtCN() {
		if (createAt == null) {
			return null;
		}
		return DateFormatProvider.DATE_FORMAT_CN.format(createAt);
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	private static final long serialVersionUID = -1477501147441760994L;

}
