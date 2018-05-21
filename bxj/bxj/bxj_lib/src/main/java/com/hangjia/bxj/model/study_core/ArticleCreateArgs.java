package com.hangjia.bxj.model.study_core;

import com.hangjia.bxj.util.StringUtils;

import java.util.Arrays;

public class ArticleCreateArgs extends ArticleManagerEntity {
	
	private Integer id;

	/**
	 * 相关分类
	 */
	private Integer[] refTypies;
	
	/**
	 * 封面图片地址。
	 */
	private String[] images;
	
	/**
	 * 封面图片处理方式，自动截取，或手动上传。
	 */
	private String imageType;

	/**
	 * 专题banner图片
	 */
	private String bannerImage;

	public Integer[] getRefTypies() {
		return refTypies;
	}

	public void setRefTypies(Integer[] refTypies) {
		this.refTypies = refTypies;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public void setTitle(String title) {
		super.setTitle(StringUtils.clean(title));
	}
	
	@Override
	public void setSourceInfo(String sourceInfo) {
		super.setSourceInfo(StringUtils.clean(sourceInfo));
	}
	
	@Override
	public void setShareText(String shareText) {
		super.setShareText(StringUtils.clean(shareText));
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleCreateArgs [title=");
		builder.append(getTitle());
		builder.append(", typeId=");
		builder.append(getTypeId());
		builder.append(", displayType=");
		builder.append(getDisplayType());
		builder.append(", publishAt=");
		builder.append(getPublishAt());
		builder.append(", sourceInfo=");
		builder.append(getSourceInfo());
		builder.append(", content=");
		builder.append(getContent());
		builder.append(", displayStyle=");
		builder.append(getDisplayStyle());
		builder.append(", showComment=");
		builder.append(isShowComment());
		builder.append(", canComment=");
		builder.append(isCanComment());
		builder.append(", shareText=");
		builder.append(getShareText());
		builder.append(", refTypies=");
		builder.append(Arrays.toString(refTypies));
		builder.append(", images=");
		builder.append(Arrays.toString(images));
		builder.append(", imageType=");
		builder.append(imageType);
		builder.append(", clickTimesSham=").append(getClickTimesSham());
		builder.append(", sort=").append(getSort());
		builder.append("]");
		return builder.toString();
	}

	private static final long serialVersionUID = 6657058415751766529L;
	
}
