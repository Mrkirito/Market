package com.hangjia.bxj.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PlanBookproRel {
    private Long fid;

    private Long planid;

    private Integer pid;

    private Integer res;

    private String name;

    private Integer jfnx;

    private Integer jflx;

    private BigDecimal bf;

    private BigDecimal be;

    private Date ctime;

    private Integer cplx;
    
    private Integer bzlq;
    private Integer lqnl;
    private Integer dyqy;
    private Integer bjmb;
    
    private BigDecimal totalBf; // 豁免产品保费
	private String cpjc; 		// 对应产品简称
	private BigDecimal basebe;  // 产品对应的基本保额
	private Integer fllx; 		// 费率类型
	private String jflxval;     // 对应缴费年限 名称 缴费类型 名称
	private Integer bxnx;		// 对应产品的保险年限    
	private String bzqx;		// 对应保障期限
	private Integer hmstate;	// 是否存在豁免
	private String  hmids;	    // 豁免ID
    private List<PlanBookproRel> planBookproRelSons;
    
    private PlanProductMain planProductMain;
    private PlanProductConstant jfnxConstant;
    private PlanProductConstant bxnxConstant;
    private PlanProductConstant lqnlConstant;
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getPlanid() {
        return planid;
    }

    public void setPlanid(Long planid) {
        this.planid = planid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getCplx() {
        return cplx;
    }

    public void setCplx(Integer cplx) {
        this.cplx = cplx;
    }

	public List<PlanBookproRel> getPlanBookproRelSons() {
		return planBookproRelSons;
	}

	public void setPlanBookproRelSons(List<PlanBookproRel> planBookproRelSons) {
		this.planBookproRelSons = planBookproRelSons;
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

	public Integer getBzlq() {
		return bzlq;
	}

	public void setBzlq(Integer bzlq) {
		this.bzlq = bzlq;
	}

	public Integer getLqnl() {
		return lqnl;
	}

	public void setLqnl(Integer lqnl) {
		this.lqnl = lqnl;
	}

	public Integer getDyqy() {
		return dyqy;
	}

	public void setDyqy(Integer dyqy) {
		this.dyqy = dyqy;
	}

	public Integer getBjmb() {
		return bjmb;
	}

	public void setBjmb(Integer bjmb) {
		this.bjmb = bjmb;
	}

	public Integer getHmstate() {
		return hmstate;
	}

	public void setHmstate(Integer hmstate) {
		this.hmstate = hmstate;
	}

	public String getHmids() {
		return hmids;
	}

	public void setHmids(String hmids) {
		this.hmids = hmids;
	}

	public BigDecimal getTotalBf() {
		return totalBf;
	}

	public void setTotalBf(BigDecimal totalBf) {
		this.totalBf = totalBf;
	}

	public String getBzqx() {
		return bzqx;
	}

	public void setBzqx(String bzqx) {
		this.bzqx = bzqx;
	}

	public PlanProductMain getPlanProductMain() {
		return planProductMain;
	}

	public void setPlanProductMain(PlanProductMain planProductMain) {
		this.planProductMain = planProductMain;
	}

	public PlanProductConstant getJfnxConstant() {
		return jfnxConstant;
	}

	public void setJfnxConstant(PlanProductConstant jfnxConstant) {
		this.jfnxConstant = jfnxConstant;
	}

	public PlanProductConstant getBxnxConstant() {
		return bxnxConstant;
	}

	public void setBxnxConstant(PlanProductConstant bxnxConstant) {
		this.bxnxConstant = bxnxConstant;
	}

	public PlanProductConstant getLqnlConstant() {
		return lqnlConstant;
	}

	public void setLqnlConstant(PlanProductConstant lqnlConstant) {
		this.lqnlConstant = lqnlConstant;
	}	
	
}