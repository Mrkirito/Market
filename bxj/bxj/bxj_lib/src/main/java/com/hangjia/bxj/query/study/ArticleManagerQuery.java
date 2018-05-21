package com.hangjia.bxj.query.study;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.util.StringUtils;

public class ArticleManagerQuery extends BaseCommonQuery {

	private Integer id;
	
	private String title;
	
	private String display;
	
	private Integer typeId;
	
	/**
	 * 显示类型，置顶、普通
	 */
	private String displayType;
	
	private String sortAs;
	
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
		this.title = StringUtils.clean(title);
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = StringUtils.clean(display);
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getSortAs() {
		return sortAs;
	}

	public void setSortAs(String sortAs) {
		this.sortAs = sortAs;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = StringUtils.clean(displayType);
	}

	@Override
	public void setOrderBy(String orderBy) {
		super.setOrderBy(StringUtils.clean(orderBy));
	}

	private static final long serialVersionUID = -2500228869407645903L;

}
