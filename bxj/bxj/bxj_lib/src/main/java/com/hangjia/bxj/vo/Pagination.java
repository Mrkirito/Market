package com.hangjia.bxj.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询对象。
 * @author K9999
 *
 */
public class Pagination<T> implements Serializable {

	/**
	 * 总记录数。
	 */
	private final int total;
	
	/**
	 * 记录列表。
	 */
	private final List<T> rows;
	
	/**
	 * 构造分页包装对象。
	 * @param total 总记录数。
	 * @param rows 记录列表，不可为null，无记录时应该是空集。
	 */
	public Pagination(int total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public List<?> getRows() {
		return rows;
	}

	private static final long serialVersionUID = 5026099605797151180L;

}
