package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 冠军论坛提取码。
 * @author K9999
 *
 */
public class ChampionExtract implements Serializable {

	/**
	 * 主键、唯一标识，同时也是提取，固定长度4位。
	 */
	private String id;
	
	/**
	 * 提取码的有效开始时间，如果早于此时间，提取码不可用。如为空表示不限定。
	 */
	private Date beginAt;
	
	/**
	 * 提取码有效结束时间，晚于此时间的提取码已过期。如为空表示不限定。
	 */
	private Date endAt;
	
	/**
	 * 此提取码指向的链接地址，不为空。
	 */
	private String location;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBeginAt() {
		return beginAt;
	}

	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private static final long serialVersionUID = 8325686691290976823L;

}
