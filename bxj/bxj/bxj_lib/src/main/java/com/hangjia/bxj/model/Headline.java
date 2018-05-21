package com.hangjia.bxj.model;

public class Headline extends BaseModel{
	
	private Integer newsType; // 新闻类型

	private Long id; // id
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	
}