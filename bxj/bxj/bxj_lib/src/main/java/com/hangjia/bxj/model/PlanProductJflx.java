package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class PlanProductJflx implements Serializable {
	private static final long serialVersionUID = -4369447535603864677L;

	private Integer fid;

    private String name;

    private Integer qy;

    private Date ctime;
    
    private Integer lxnum;
    
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

	public Integer getLxnum() {
		return lxnum;
	}

	public void setLxnum(Integer lxnum) {
		this.lxnum = lxnum;
	}
    
}