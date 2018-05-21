package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * （产品说明会）邀请函 简单数据。
 * 此数据只返回名称和参会人数。
 * @author K9999
 *
 */
public class InvitationSimple implements Serializable {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 产说会名称
	 */
	private String name;
	
	/**
	 * 报名人数。
	 */
	private Integer joinCount;
	
	private Date dateAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}
	
	public Date getDateAt() {
		return dateAt;
	}

	public void setDateAt(Date dateAt) {
		this.dateAt = dateAt;
	}
	
	public String getDateAtCN() {
		if (dateAt == null) {
			return null;
		}
		return DateFormatProvider.DATE_FULL_FORMAT_CN.format(dateAt);
	}

	private static final long serialVersionUID = 5232999781913670350L;
	
}
