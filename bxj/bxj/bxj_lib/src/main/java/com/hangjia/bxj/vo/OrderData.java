package com.hangjia.bxj.vo;

/**
 * @ClassName: OrderData
 * @Description: 排序对象
 * @author: bei.zhang
 * @date: 2016年4月12日 上午10:49:59
 */
public class OrderData {

	private String field; // 排序字段
	private OrderMethod direction; // 排序方向

	public OrderData(String field, OrderMethod direction) {
		this.field = field;
		this.direction = direction;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public OrderMethod getDirection() {
		return direction;
	}

	public void setDirection(OrderMethod direction) {
		this.direction = direction;
	}

}
