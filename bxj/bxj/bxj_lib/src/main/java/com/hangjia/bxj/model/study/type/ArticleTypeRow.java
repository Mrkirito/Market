package com.hangjia.bxj.model.study.type;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ArticleTypeRow extends ArticleTypeEntity {

	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date createAt;
	
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date updateAt;
	
	private Integer articlesCount;
	
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

	public Integer getArticlesCount() {
		return articlesCount;
	}

	public void setArticlesCount(Integer articlesCount) {
		this.articlesCount = articlesCount;
	}

	private static final long serialVersionUID = -8879576512715317834L;

}
