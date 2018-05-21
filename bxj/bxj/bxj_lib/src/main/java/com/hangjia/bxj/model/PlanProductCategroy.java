package com.hangjia.bxj.model;

import java.io.Serializable;

public class PlanProductCategroy implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -451315360233568575L;

	private Integer fid;

    private String cname;

    private Integer state;

    private Integer sort;
    
    private Integer selected;

    private Integer gs;
    
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public Integer getGs() {
		return gs;
	}

	public void setGs(Integer gs) {
		this.gs = gs;
	}
    
    
}