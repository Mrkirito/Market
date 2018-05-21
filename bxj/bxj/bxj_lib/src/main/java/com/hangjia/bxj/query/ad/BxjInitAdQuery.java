package com.hangjia.bxj.query.ad;

import java.io.Serializable;
import java.util.Date;

import com.hangjia.bxj.common.BaseCommonQuery;

public class BxjInitAdQuery extends BaseCommonQuery implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6273977471269955572L;

	private Long fid;

    private String imgUrl;

    private Integer action;

    private Integer status;

    private String title;

    private String openUrl;

    private Date createTime;

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
