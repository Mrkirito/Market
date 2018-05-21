package com.hangjia.bxj.vo;

import java.io.Serializable;
import java.util.Date;

public class PlanProductGs implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3628908441201913508L;

	private Integer fid;

    private String name;

    private Integer qy;

    private Date ctime;

    private Integer select=0;//默认：1=选择；0=不选择
    
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

	public Integer getSelect() {
		return select;
	}

	public void setSelect(Integer select) {
		this.select = select;
	}
    
}