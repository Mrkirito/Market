package com.hangjia.bxj.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询对象。
 */
@SuppressWarnings("unused")
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 5026099605797151180L;

	 /** 总记录数 **/
	private final int total;

	/** 当前页数 **/
	private final int currPage;

	/**每页条数**/
	private final int pageSize;
	
	/** 记录列表 */
	private final List<T> rows;

	/**
	 * 构造分页包装对象。
	 * @param total 总记录数
	 * @param currPage 当前页数
	 * @param pageSize 每页条数
	 * @param rows 记录列表，不可为null，无记录时应该是空集。
	 */
	public Pagination(int total, int currPage, int pageSize, List<T> rows) {
		this.total = total;
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.rows = rows;
	}

	public int getCurrPage() {
		return currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotal() {
		return total;
	}

	public List<?> getRows() {
		return rows;
	}
}
