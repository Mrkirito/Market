package com.hangjia.bxj.model;

import java.io.Serializable;

public class BxjResponse implements Serializable {
	private static final long serialVersionUID = 8913442571063320332L;
	private String id;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
