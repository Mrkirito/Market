package com.hangjia.bxj.model.summit;

import java.io.Serializable;
import java.util.List;

/**
 * 冠军论坛课堂（讲座）。
 * 在一次峰会中，会有多个讲师的课堂。
 *
 */
public class ClassroomEntity implements Serializable {

	private Integer id;
	
	private Integer summitId;
	
	private String summitName;
	
	private String display;
	
	private String teacherName;
	
	private String title;
	
	/**
	 * 排序
	 */
	private Integer sort;
	/*
	 * 真实点击
	 */
	private Integer clickReal;
	
	/**
	 * 虚拟点击
	 */
	private Integer clickSham;
	
	/**
	 * 虚拟加真实= 总点击
	 */
	private Integer playCount;
	
	/**
	 * 讲师图片地址。
	 */
	private String imageUrl;
	
	/**
	 * 讲师图片全路径地址
	 */
	private String imgFileUrl;
	
	/**
	 * ppt图片地址。
	 */
	private String[] images;
	/**
	 * 展示 ppt图片显示
	 */
	private String[] showImages;
	
	public ClassroomEntity() {
	}
	
	public ClassroomEntity(Integer summitId, String summitName) {
		this.summitId = summitId;
		this.summitName = summitName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSummitId() {
		return summitId;
	}

	public void setSummitId(Integer summitId) {
		this.summitId = summitId;
	}

	public String getSummitName() {
		return summitName;
	}

	public void setSummitName(String summitName) {
		this.summitName = summitName;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getClickReal() {
		return clickReal;
	}

	public void setClickReal(Integer clickReal) {
		this.clickReal = clickReal;
	}

	public Integer getClickSham() {
		return clickSham;
	}

	public void setClickSham(Integer clickSham) {
		this.clickSham = clickSham;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getImgFileUrl() {
		return imgFileUrl;
	}

	public void setImgFileUrl(String imgFileUrl) {
		this.imgFileUrl = imgFileUrl;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}
	
	public String[] getShowImages() {
		return showImages;
	}

	public void setShowImages(String[] showImages) {
			this.showImages = showImages;
	}
	
	private static final long serialVersionUID = 1516553213898064867L;

}
