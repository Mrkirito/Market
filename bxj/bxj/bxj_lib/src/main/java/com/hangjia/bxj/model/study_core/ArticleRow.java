package com.hangjia.bxj.model.study_core;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 学习管理模块【文章】列表行数据。
 * 
 * 在学习管理模块首页【文章列表】中的每行记录，都是1个此类实例。
 * 
 * @author K9999
 *
 */
public class ArticleRow implements Serializable {

	/**
	 * 主键、唯一标识。
	 */
	private Integer id;
	
	/**
	 * 文章标题。
	 */
	private String title;
	
	/**
	 * 真实点击数。
	 */
	private Integer clickTimesReal;
	
	/**
	 * 发布时间。
	 */
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date publishAt;
	
	/**
	 * 分类名称。
	 */
	private String typeName;
	
	/**
	 * 是否显示（正常、或已删除）
	 */
	private Boolean display;
	
	/**
	 * 文章创建时间（录入时间）。
	 */
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date createAt;
	
	/**
	 * 最后修改时间。
	 */
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date updateAt;
	
	/**
	 * 评论数。
	 */
	private Integer commentCount;
	
	/**
	 * 排序权重值，升序。
	 */
	private Integer sort;
	
	private String displayType;
	private String tags;

	private String classify;

	private String classify1;

	private String classify2;

	private Integer num1;

	private Integer num2;

	private Integer num3;

	private Integer score;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getClickTimesReal() {
		return clickTimesReal;
	}

	public void setClickTimesReal(Integer clickTimesReal) {
		this.clickTimesReal = clickTimesReal;
	}

	public Date getPublishAt() {
		return publishAt;
	}

	public void setPublishAt(Date publishAt) {
		this.publishAt = publishAt;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
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

	private static final long serialVersionUID = 2353914267205562240L;
	
}
