package com.hangjia.bxj.model;

import java.io.Serializable;

/**
 * 产品简单数据，用于列表页显示。完整数据请参见 {@link PlanProductMain}。
 * @author K9999
 * @see PlanProductMain
 */
public class PlanProductSimple implements Serializable {

	private Integer fid;

    private String name;
    
    private Integer gs;
    
    private String imageURL;
    
    private String cplb;
    
    private String cpms;
    /***
     * 计划书跳转链接
     */
    private String linkUrl;
    
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getCplb() {
		return cplb;
	}

	public void setCplb(String cplb) {
		this.cplb = cplb;
	}

	public String getCpms() {
		return cpms;
	}

	public void setCpms(String cpms) {
		this.cpms = cpms;
	}
    
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	private static final long serialVersionUID = -6309762361478079477L;
	
}
