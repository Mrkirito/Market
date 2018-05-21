package com.hangjia.bxj.model;

import java.math.BigDecimal;
import java.util.Date;

public class PlanBookproRelSon {
    private Long fid;

    private Long rid;

    private Long planid;

    private Integer fpid;

    private Integer pid;

    private Integer res;

    private Integer jfnx;

    private Integer jflx;

    private BigDecimal bf;

    private BigDecimal be;

    private Date ctime;
    
	private String cpjc; 		// 对应产品简称
	private BigDecimal basebe;  // 产品对应的基本保额
	private Integer fllx; 		// 费率类型
	private String jflxval;     // 费率类型    对应缴费年限 名称 缴费类型 名称
	private Integer bxnx;		// 对应产品的保险年限    
	private String bzqx;		// 对应产品的保险年限    
	private Integer cplx;		// 产品类型    
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPlanid() {
        return planid;
    }

    public void setPlanid(Long planid) {
        this.planid = planid;
    }

    public Integer getFpid() {
        return fpid;
    }

    public void setFpid(Integer fpid) {
        this.fpid = fpid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public Integer getJfnx() {
        return jfnx;
    }

    public void setJfnx(Integer jfnx) {
        this.jfnx = jfnx;
    }

    public Integer getJflx() {
        return jflx;
    }

    public void setJflx(Integer jflx) {
        this.jflx = jflx;
    }

    public BigDecimal getBf() {
        return bf;
    }

    public void setBf(BigDecimal bf) {
        this.bf = bf;
    }

    public BigDecimal getBe() {
        return be;
    }

    public void setBe(BigDecimal be) {
        this.be = be;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

	public String getCpjc() {
		return cpjc;
	}

	public void setCpjc(String cpjc) {
		this.cpjc = cpjc;
	}

	public String getJflxval() {
		return jflxval;
	}

	public void setJflxval(String jflxval) {
		this.jflxval = jflxval;
	}

	public BigDecimal getBasebe() {
		return basebe;
	}

	public void setBasebe(BigDecimal basebe) {
		this.basebe = basebe;
	}

	public Integer getFllx() {
		return fllx;
	}

	public void setFllx(Integer fllx) {
		this.fllx = fllx;
	}

	public Integer getBxnx() {
		return bxnx;
	}

	public void setBxnx(Integer bxnx) {
		this.bxnx = bxnx;
	}

	public Integer getCplx() {
		return cplx;
	}

	public void setCplx(Integer cplx) {
		this.cplx = cplx;
	}

	public String getBzqx() {
		return bzqx;
	}

	public void setBzqx(String bzqx) {
		this.bzqx = bzqx;
	}	
	
}