package com.hangjia.bxj.query.summit;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.util.StringUtils;

public class SummitManagerQuery extends BaseCommonQuery {

	private Integer id;
	
	private String name;
	
	private String display;
	
	private String title;
	/** 开始查询 下标**/
	private Integer firstIndex;
	
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
		this.name = StringUtils.clean(name);
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
   
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(Integer firstIndex) {
		this.firstIndex = firstIndex;
	}



	private static final long serialVersionUID = -1867133753322287348L;

}
