package com.znb.cms.model.mapper;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业雇主用户信息（投保企业）
 * @author fhj
 * @date 2017年4月1日 下午12:17:20
 * @version <b>1.0.0</b>
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 170923514555063750L;
	
	private Integer id;
	private String userName;//企业名
	private String mobile;	//企业手机(账户)
	private Date createTime;// 创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}