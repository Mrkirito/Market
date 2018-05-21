package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户意见或建议反馈记录。
 * @author K9999
 *
 */
public class MemberIdea implements Serializable {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 用户ID，提出建议的用户。
	 */
	private Integer userId;
	
	/**
	 * 建议内容。
	 */
	private String text;
	
	/**
	 * 联系方式描述，格式多种多样，由用户填写，人工分辨。
	 * 如：微信号12313、QQ：312312、手机号、宅电等。
	 */
	private String contactInformation;
    private Integer state;
    private String mark;
	/**
	 * 提交时间。
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	private static final long serialVersionUID = -8575756549938535678L;

}
