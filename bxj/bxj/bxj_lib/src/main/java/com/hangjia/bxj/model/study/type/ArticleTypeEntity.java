package com.hangjia.bxj.model.study.type;

import com.hangjia.bxj.model.study.ArticleType;

public class ArticleTypeEntity extends ArticleType {

	private Integer sort;
	
	private Boolean display;
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	private static final long serialVersionUID = 3357955597637328962L;

}
