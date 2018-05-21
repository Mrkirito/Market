package com.hangjia.bxj.query.ticket;

import com.hangjia.bxj.common.BaseCommonQuery;

public class SalesTicketDocumentQuery extends BaseCommonQuery {
	private Integer basicId;
	private Integer state;
	private Integer start;
	private Integer end;
	
	
	private String floor;
	private String area;
	private String rows;
	private String number;
	public Integer getBasicId() {
		return basicId;
	}
	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
