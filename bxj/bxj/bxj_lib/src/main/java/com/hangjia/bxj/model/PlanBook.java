package com.hangjia.bxj.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PlanBook {
    private Long fid;

    private String name;

    private Integer age;

    private Integer sex;

    private BigDecimal njbf;

    private Integer res;

    private Integer gs;

    private Integer pid;

    private BigDecimal bf;

    private String author;

    private Date ctime;
    
    private String planxh;
    private Integer appOs;
    private String appVersionCode;
    private String appVersionName;
    
	private Integer kchs;
	private String proRels;
	private Integer jfnxVal;
	private BigDecimal totalBf;
	private BigDecimal zxBe;
    private List<PlanBookproRel> rels;
    private List<PlanProductQy> qys;
	private Integer ageGap;// 年龄相差值
	private BigDecimal bfGap;// 保费相差值
	private String bzqx;	//保障期限
	private PlanBookUserRel userRel;
	private Integer userId;
	private Long customerId;
	
	
	private Long invitationId;//邀请函Id
	private Long voiceId;//语音Id
	private String userCardName;
	private String phone;
	
	private Long articleId;
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigDecimal getNjbf() {
        return njbf;
    }

    public void setNjbf(BigDecimal njbf) {
        this.njbf = njbf;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public Integer getGs() {
        return gs;
    }

    public void setGs(Integer gs) {
        this.gs = gs;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getBf() {
        return bf;
    }

    public void setBf(BigDecimal bf) {
        this.bf = bf;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

	public List<PlanBookproRel> getRels() {
		return rels;
	}
	public void setRels(List<PlanBookproRel> rels) {
		this.rels = rels;
	}

	public Integer getAgeGap() {
		return ageGap;
	}

	public void setAgeGap(Integer ageGap) {
		this.ageGap = ageGap;
	}

	public BigDecimal getBfGap() {
		return bfGap;
	}

	public void setBfGap(BigDecimal bfGap) {
		this.bfGap = bfGap;
	}

	public List<PlanProductQy> getQys() {
		return qys;
	}

	public void setQys(List<PlanProductQy> qys) {
		this.qys = qys;
	}

	public String getPlanxh() {
		return planxh;
	}

	public void setPlanxh(String planxh) {
		this.planxh = planxh;
	}

	public PlanBookUserRel getUserRel() {
		return userRel;
	}

	public void setUserRel(PlanBookUserRel userRel) {
		this.userRel = userRel;
	}

	public Integer getKchs() {
		return kchs;
	}

	public void setKchs(Integer kchs) {
		this.kchs = kchs;
	}

	public Integer getJfnxVal() {
		return jfnxVal;
	}

	public void setJfnxVal(Integer jfnxVal) {
		this.jfnxVal = jfnxVal;
	}

	public BigDecimal getTotalBf() {
		return totalBf;
	}

	public void setTotalBf(BigDecimal totalBf) {
		this.totalBf = totalBf;
	}

	public BigDecimal getZxBe() {
		return zxBe;
	}

	public void setZxBe(BigDecimal zxBe) {
		this.zxBe = zxBe;
	}

	public String getBzqx() {
		return bzqx;
	}

	public void setBzqx(String bzqx) {
		this.bzqx = bzqx;
	}

	public String getProRels() {
		return proRels;
	}

	public void setProRels(String proRels) {
		this.proRels = proRels;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	public Long getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(Long voiceId) {
		this.voiceId = voiceId;
	}

	public String getUserCardName() {
		return userCardName;
	}

	public void setUserCardName(String userCardName) {
		this.userCardName = userCardName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAppOs() {
		return appOs;
	}

	public void setAppOs(Integer appOs) {
		this.appOs = appOs;
	}

	public String getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}	
	
}