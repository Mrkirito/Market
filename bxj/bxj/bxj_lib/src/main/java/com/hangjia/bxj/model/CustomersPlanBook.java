package com.hangjia.bxj.model;

import java.util.Date;

/**
 * 客户的计划书。
 * 此类是一个简单的计划书缩引，由关联查询得到。
 * 在客户详情中的【客户计划书列表】中显示。
 * @author K9999
 *
 */
public class CustomersPlanBook {
	
	private Integer id;

	private String name;
	
	private Date createAt;
	
	/**
	 * 读取状态，0：客户未查阅，1：已查阅。
	 */
	private Integer readStatus;
	
	/**
	 * 计划书和客户的关联表ID，删除客户的计划书，实际上是删除关联关系，加入这个字段删除比较高效。
	 */
	private Integer relId;
	
	private Long customerId;
	private Long bookId;
	
	private Integer flag;
	private String bookName;
	private Long userId;
	
	private String date;
	
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
		this.name = name;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}
	
	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	/**
	 * 返回客户是否已查阅。
	 * @return
	 */
	public boolean isReaded() {
		return readStatus == null ? false : (readStatus == 1 ? true : false);
	}
	
	/**
	 * 返回【创建时间】的中文日期格式：yyyy年MM月dd日。
	 * @return 【创建时间】的中文日期格式，或 {@code null}（createAt 为空）。
	 */
	public String getCreateAtCN() {
		return createAt == null ? null : DateFormatProvider.DATE_FORMAT_CN.format(createAt);
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
