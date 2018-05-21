package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class PlanProductJfnx implements Serializable {
	private static final long serialVersionUID = 1664308111584468713L;

	private Integer fid;

    private String name;

    private Integer qy;

    private Date ctime;
    
    private Integer nxnum;
    
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
        this.name = name == null ? null : name.trim();
    }

    public Integer getQy() {
        return qy;
    }

    public void setQy(Integer qy) {
        this.qy = qy;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

	public Integer getNxnum() {
		return nxnum;
	}

	public void setNxnum(Integer nxnum) {
		this.nxnum = nxnum;
	}
    
}