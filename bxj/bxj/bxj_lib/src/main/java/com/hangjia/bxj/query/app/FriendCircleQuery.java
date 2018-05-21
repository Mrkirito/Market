package com.hangjia.bxj.query.app;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * 朋友圈神器 后台查询
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年7月4日 上午11:40:38
 */

public class FriendCircleQuery extends BaseCommonQuery{

	private static final long serialVersionUID = -7468621596343710116L;
	private Long id; // id
    private Integer sort; // 排序
	private String title; // 标题
	private Integer type; //类型
	private Integer	status; //是否 上线
	private Date publishTime; // 上线时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
