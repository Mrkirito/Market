package com.hangjia.bxj.model.study;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

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

	private static final long serialVersionUID = 2353914267205562240L;
	
}
