package com.hangjia.bxj.model.study;

import java.io.Serializable;

public class ArticleType implements Serializable {

	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private static final long serialVersionUID = -6735915117454853281L;

}
