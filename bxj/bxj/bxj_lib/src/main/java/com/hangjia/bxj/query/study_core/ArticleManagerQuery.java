package com.hangjia.bxj.query.study_core;

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
	private String tags;

	private String classify;
	private String classify1;
	private String classify2;

	private Integer num1;

	private Integer num2;

	private Integer num3;

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

	@Override
	public void setOrderBy(String orderBy) {
		super.setOrderBy(StringUtils.clean(orderBy));
	}

	private static final long serialVersionUID = -2500228869407645903L;

}
