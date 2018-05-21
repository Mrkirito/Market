package com.hangjia.bxj.query.app;

import com.hangjia.bxj.common.BaseCommonQuery;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:32:53 
* @version 1.0 
*/
public class HeadlineBannerQuery extends BaseCommonQuery {

	private static final long serialVersionUID = -7468621596343710116L;
	private Long id; // id
	private Integer upOrDown; // 向上 1 向下 2
    private Integer sort; // 排序
	private String title; // 标题
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUpOrDown() {
		return upOrDown;
	}
	public void setUpOrDown(Integer upOrDown) {
		this.upOrDown = upOrDown;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
