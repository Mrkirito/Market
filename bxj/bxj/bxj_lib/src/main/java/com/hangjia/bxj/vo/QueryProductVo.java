package com.hangjia.bxj.vo;
public class QueryProductVo {
	private String  name;
	private Integer gs;
	private Integer pageSize = 10;
	private Integer currentPage;
	private Integer offset;
	private Integer xz;
	
	private Integer cfid;//plan_product_categroy.fid
	
	public QueryProductVo() {
		super();
	}
	public QueryProductVo(Integer gs, Integer xz) {
		this.gs = gs;
		this.xz = xz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGs() {
		return gs;
	}
	public void setGs(Integer gs) {
		this.gs = gs;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getXz() {
		return xz;
	}
	public void setXz(Integer xz) {
		this.xz = xz;
	}
	public Integer getCfid() {
		return cfid;
	}
	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}
	
	
}
