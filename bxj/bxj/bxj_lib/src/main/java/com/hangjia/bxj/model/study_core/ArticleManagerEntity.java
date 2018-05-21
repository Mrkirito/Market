package com.hangjia.bxj.model.study_core;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ArticleManagerEntity implements Serializable {

	private String title;
	
	private Integer typeId;
	
	private String displayType;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date publishAt;
	
	private String sourceInfo;
	
	private String content;
	
	private String displayStyle;
	
	private boolean showComment;
	
	private boolean canComment;
	
	private String shareText;
	
	private String shareImage;

	private String tags;

	private String classify;
	private String classify1;
	private String classify2;

	private Integer num1;

	private Integer num2;

	private Integer num3;

	private Integer score;
	/**
	 * 排序字段，升序。
	 */
	private Integer sort;
	
	/**
	 * 虚拟点击数。
	 */
	private Integer clickTimesSham;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public Date getPublishAt() {
		return publishAt;
	}

	public void setPublishAt(Date publishAt) {
		this.publishAt = publishAt;
	}

	public String getSourceInfo() {
		return sourceInfo;
	}

	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDisplayStyle() {
		return displayStyle;
	}

	public void setDisplayStyle(String displayStyle) {
		this.displayStyle = displayStyle;
	}

	public boolean isShowComment() {
		return showComment;
	}

	public void setShowComment(boolean showComment) {
		this.showComment = showComment;
	}

	public boolean isCanComment() {
		return canComment;
	}

	public void setCanComment(boolean canComment) {
		this.canComment = canComment;
	}

	public String getShareText() {
		return shareText;
	}

	public void setShareText(String shareText) {
		this.shareText = shareText;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getClickTimesSham() {
		return clickTimesSham;
	}

	public void setClickTimesSham(Integer clickTimesSham) {
		this.clickTimesSham = clickTimesSham;
	}

	public String getShareImage() {
		return shareImage;
	}

	public void setShareImage(String shareImage) {
		this.shareImage = shareImage;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public Integer getNum3() {
		return num3;
	}

	public void setNum3(Integer num3) {
		this.num3 = num3;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getClassify1() {
		return classify1;
	}

	public void setClassify1(String classify1) {
		this.classify1 = classify1;
	}

	public String getClassify2() {
		return classify2;
	}

	public void setClassify2(String classify2) {
		this.classify2 = classify2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleManagerEntity [title=");
		builder.append(title);
		builder.append(", typeId=");
		builder.append(typeId);
		builder.append(", displayType=");
		builder.append(displayType);
		builder.append(", publishAt=");
		builder.append(publishAt);
		builder.append(", sourceInfo=");
		builder.append(sourceInfo);
		builder.append(", content=");
		builder.append(content);
		builder.append(", displayStyle=");
		builder.append(displayStyle);
		builder.append(", showComment=");
		builder.append(showComment);
		builder.append(", canComment=");
		builder.append(canComment);
		builder.append(", shareText=");
		builder.append(shareText);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", clickTimesSham=");
		builder.append(clickTimesSham);
		builder.append("]");
		return builder.toString();
	}

	private static final long serialVersionUID = 1326795838256338066L;
	
}
