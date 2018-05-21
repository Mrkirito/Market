package com.znb.cms.model.mapper;

import java.util.Date;

import com.znb.cms.common.BaseCommonQuery;

public class Suggestion extends BaseCommonQuery{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4312036043297621914L;

	private Integer id;

    private String mobile;

    private Integer userId;

    private String opinion;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}