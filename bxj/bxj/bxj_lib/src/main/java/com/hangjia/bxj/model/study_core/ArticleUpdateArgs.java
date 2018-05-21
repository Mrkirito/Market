package com.hangjia.bxj.model.study_core;

public class ArticleUpdateArgs extends ArticleManagerEntity {

	private Integer id;
	
	private String[] refTypies;
	
	private String[] images;

	/**
	 * 专题banner图片
	 */
	private String bannerImage;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String[] getRefTypies() {
		return refTypies;
	}

	public void setRefTypies(String refTypies) {
		if (refTypies != null) {
			this.refTypies = refTypies.split(",");
		}
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String images) {
		if (images != null) {
			this.images = images.split(",");
		}
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	private static final long serialVersionUID = 3010460556101104685L;

}
