package com.hangjia.bxj.vo.banner;

import java.io.Serializable;

public class BannerManagerUpdateDisplayParameter implements Serializable {

	private final Object display;
	
	private final Integer id;
	
	public BannerManagerUpdateDisplayParameter(Integer id, Object display) {
		this.id = id;
		this.display = display;
	}
	
	public Object getDisplay() {
		return display;
	}
	
	public Integer getId() {
		return id;
	}

	private static final long serialVersionUID = 7779457965425543997L;

}
