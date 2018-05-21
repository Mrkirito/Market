package com.hangjia.bxj.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PlanProductMain implements Serializable {
	private static final long serialVersionUID = 2136967132653880095L;

	private Integer fid;

    private String name;

    private String cpjc;

    private Integer cptype;

    private Integer gs;

    private Integer sxj;

    private Integer px;

    private Integer creater;

    private Date ctime;

    private Date uptime;

    private String img;

    private Integer bq;

    private BigDecimal jbbe;

    private Integer tbnlks;

    private Integer tbnljs;

    private Integer xz;

    private String tk;

    private Integer fllx;

    private Integer cdmc;

    private Integer cplx;

    private Long ver;

    private String groupids;

    private Integer bxnx;

    private String cpbq;

    private Integer bjmb;

    private String hmids;

    private Integer hmstate;

    private String cplb;

    private String cpms;

    private String link;

    private Integer calflag;

    private String groupName;

    private Integer zxflag;

    private List<PlanProductQy> planProductQies;
    
    private List<PlanProductConstant> productConstants;
    
    private List<PlanProductRelates> childs;
    
    private List<PlanProductRelates> mustChilds;
    
    private List<Integer> ages;
    
	private String bbrqy;

    private String tbrqy;

    private String mc;

    private String cpts;
    private Integer qyMark;
    
    private Integer control;
    
    private Integer tbrinfo;
    
    private String adminQys;
    
    private Integer genpage;
    private String gsName;
    private String img2;
    private Integer cpje;
    
    private Integer bonusState;
    private BigDecimal bonusNum;
    
    private List<PlanProductCategroy> categroys;
    private String categroyIds;
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

    public String getCpjc() {
        return cpjc;
    }

    public void setCpjc(String cpjc) {
        this.cpjc = cpjc == null ? null : cpjc.trim();
    }

    public Integer getCptype() {
        return cptype;
    }

    public void setCptype(Integer cptype) {
        this.cptype = cptype;
    }

    public Integer getGs() {
        return gs;
    }

    public void setGs(Integer gs) {
        this.gs = gs;
    }

    public Integer getSxj() {
        return sxj;
    }

    public void setSxj(Integer sxj) {
        this.sxj = sxj;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getBq() {
        return bq;
    }

    public void setBq(Integer bq) {
        this.bq = bq;
    }

    public BigDecimal getJbbe() {
        return jbbe;
    }

    public void setJbbe(BigDecimal jbbe) {
        this.jbbe = jbbe;
    }

    public Integer getTbnlks() {
        return tbnlks;
    }

    public void setTbnlks(Integer tbnlks) {
        this.tbnlks = tbnlks;
    }

    public Integer getTbnljs() {
        return tbnljs;
    }

    public void setTbnljs(Integer tbnljs) {
        this.tbnljs = tbnljs;
    }

    public Integer getXz() {
        return xz;
    }

    public void setXz(Integer xz) {
        this.xz = xz;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk == null ? null : tk.trim();
    }

    public Integer getFllx() {
        return fllx;
    }

    public void setFllx(Integer fllx) {
        this.fllx = fllx;
    }

    public Integer getCdmc() {
        return cdmc;
    }

    public void setCdmc(Integer cdmc) {
        this.cdmc = cdmc;
    }

    public Integer getCplx() {
        return cplx;
    }

    public void setCplx(Integer cplx) {
        this.cplx = cplx;
    }

    public Long getVer() {
        return ver;
    }

    public void setVer(Long ver) {
        this.ver = ver;
    }

    public String getGroupids() {
        return groupids;
    }

    public void setGroupids(String groupids) {
        this.groupids = groupids == null ? null : groupids.trim();
    }

    public Integer getBxnx() {
        return bxnx;
    }

    public void setBxnx(Integer bxnx) {
        this.bxnx = bxnx;
    }

    public String getCpbq() {
        return cpbq;
    }

    public void setCpbq(String cpbq) {
        this.cpbq = cpbq == null ? null : cpbq.trim();
    }

    public Integer getBjmb() {
        return bjmb;
    }

    public void setBjmb(Integer bjmb) {
        this.bjmb = bjmb;
    }

    public String getHmids() {
        return hmids;
    }

    public void setHmids(String hmids) {
        this.hmids = hmids == null ? null : hmids.trim();
    }

    public Integer getHmstate() {
        return hmstate;
    }

    public void setHmstate(Integer hmstate) {
        this.hmstate = hmstate;
    }

    public String getCplb() {
        return cplb;
    }

    public void setCplb(String cplb) {
        this.cplb = cplb == null ? null : cplb.trim();
    }

    public String getCpms() {
        return cpms;
    }

    public void setCpms(String cpms) {
        this.cpms = cpms == null ? null : cpms.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Integer getCalflag() {
        return calflag;
    }

    public void setCalflag(Integer calflag) {
        this.calflag = calflag;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getZxflag() {
        return zxflag;
    }

    public void setZxflag(Integer zxflag) {
        this.zxflag = zxflag;
    }

	public List<PlanProductConstant> getProductConstants() {
		return productConstants;
	}

	public void setProductConstants(List<PlanProductConstant> productConstants) {
		this.productConstants = productConstants;
	}

	public List<PlanProductRelates> getChilds() {
		return childs;
	}

	public void setChilds(List<PlanProductRelates> childs) {
		this.childs = childs;
	}

	public List<Integer> getAges() {
		return ages;
	}

	public void setAges(List<Integer> ages) {
		this.ages = ages;
	}

	public List<PlanProductQy> getPlanProductQies() {
		return planProductQies;
	}

	public void setPlanProductQies(List<PlanProductQy> planProductQies) {
		this.planProductQies = planProductQies;
	}

	public String getBbrqy() {
		return bbrqy;
	}

	public void setBbrqy(String bbrqy) {
		this.bbrqy = bbrqy;
	}

	public String getTbrqy() {
		return tbrqy;
	}

	public void setTbrqy(String tbrqy) {
		this.tbrqy = tbrqy;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getCpts() {
		return cpts;
	}

	public void setCpts(String cpts) {
		this.cpts = cpts;
	}

	public List<PlanProductRelates> getMustChilds() {
		return mustChilds;
	}

	public void setMustChilds(List<PlanProductRelates> mustChilds) {
		this.mustChilds = mustChilds;
	}

	public Integer getQyMark() {
		return qyMark;
	}

	public void setQyMark(Integer qyMark) {
		this.qyMark = qyMark;
	}

	public Integer getControl() {
		return control;
	}

	public void setControl(Integer control) {
		this.control = control;
	}

	public Integer getTbrinfo() {
		return tbrinfo;
	}

	public void setTbrinfo(Integer tbrinfo) {
		this.tbrinfo = tbrinfo;
	}

	public String getAdminQys() {
		return adminQys;
	}

	public void setAdminQys(String adminQys) {
		this.adminQys = adminQys;
	}

	public Integer getGenpage() {
		return genpage;
	}

	public void setGenpage(Integer genpage) {
		this.genpage = genpage;
	}

	public String getGsName() {
		return gsName;
	}

	public void setGsName(String gsName) {
		this.gsName = gsName;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public Integer getCpje() {
		return cpje;
	}

	public void setCpje(Integer cpje) {
		this.cpje = cpje;
	}

	public Integer getBonusState() {
		return bonusState;
	}

	public void setBonusState(Integer bonusState) {
		this.bonusState = bonusState;
	}

	public BigDecimal getBonusNum() {
		return bonusNum;
	}

	public void setBonusNum(BigDecimal bonusNum) {
		this.bonusNum = bonusNum;
	}

	public List<PlanProductCategroy> getCategroys() {
		return categroys;
	}

	public void setCategroys(List<PlanProductCategroy> categroys) {
		this.categroys = categroys;
	}

	public String getCategroyIds() {
		return categroyIds;
	}

	public void setCategroyIds(String categroyIds) {
		this.categroyIds = categroyIds;
	}
	
}