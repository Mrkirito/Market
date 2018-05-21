package com.hangjia.bxj.model;

import java.io.Serializable;

/**
 * 轮播图片。
 * @author K9999
 *
 */
public class Banner implements Serializable {

	private Integer fid;
	
	/**
	 * 标题，可为空。
	 */
	private String title;
	
	/**
	 * 链接地址。
	 */
	private String location;
	
	/**
	 * 图片地址。
	 */
	private String imageURL;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	private static final long serialVersionUID = -4493246526838535472L;
	
}
