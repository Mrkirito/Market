package com.hangjia.bxj.model;

import java.io.Serializable;

public class PlanProductRelates implements Serializable{
	private static final long serialVersionUID = 5751792172283048029L;
	private Integer pid;
	private PlanProductMain product;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public PlanProductMain getProduct() {
		return product;
	}
	public void setProduct(PlanProductMain product) {
		this.product = product;
	}	
}
