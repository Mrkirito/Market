package com.hangjia.bxj.query.qixi;

import com.hangjia.bxj.common.BaseCommonQuery;

public class OrdersQuery extends BaseCommonQuery{

	private Integer goodsId;
	private String payType;
	private Integer status;
	private String name;
	private String phone;
	private String expressNo;
	private String createTime1;
	private String createTime2;
	private String addressTime1;
	private String addressTime2;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Override
	public Integer getStatus() {
		return status;
	}

	@Override
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getCreateTime1() {
		return createTime1;
	}

	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}

	public String getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(String createTime2) {
		this.createTime2 = createTime2;
	}

	public String getAddressTime1() {
		return addressTime1;
	}

	public void setAddressTime1(String addressTime1) {
		this.addressTime1 = addressTime1;
	}

	public String getAddressTime2() {
		return addressTime2;
	}

	public void setAddressTime2(String addressTime2) {
		this.addressTime2 = addressTime2;
	}
}
