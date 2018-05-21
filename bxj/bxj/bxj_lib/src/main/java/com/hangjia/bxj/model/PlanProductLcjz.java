package com.hangjia.bxj.model;

import java.math.BigDecimal;

public class PlanProductLcjz {
    private Long fid;

    private Integer pid;

    private Integer jfnx;

    private Integer sex;

    private Integer age;

    private String low;

    private String mid;

    private String high;
    
    private BigDecimal be;
    
    private Long ver;

    /**load **/
	private BigDecimal cal60Age;
	private BigDecimal cal60AgePer;
	private BigDecimal cal60AgeSy;
	private BigDecimal cal70AgeSy;
	private BigDecimal cal80AgeSy;
	private BigDecimal cal90AgeSy;
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getJfnx() {
        return jfnx;
    }

    public void setJfnx(Integer jfnx) {
        this.jfnx = jfnx;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low == null ? null : low.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high == null ? null : high.trim();
    }

    public Long getVer() {
        return ver;
    }

    public void setVer(Long ver) {
        this.ver = ver;
    }

	public BigDecimal getBe() {
		return be;
	}

	public void setBe(BigDecimal be) {
		this.be = be;
	}

	public BigDecimal getCal60Age() {
		return cal60Age;
	}

	public void setCal60Age(BigDecimal cal60Age) {
		this.cal60Age = cal60Age;
	}

	public BigDecimal getCal60AgePer() {
		return cal60AgePer;
	}

	public void setCal60AgePer(BigDecimal cal60AgePer) {
		this.cal60AgePer = cal60AgePer;
	}

	public BigDecimal getCal60AgeSy() {
		return cal60AgeSy;
	}

	public void setCal60AgeSy(BigDecimal cal60AgeSy) {
		this.cal60AgeSy = cal60AgeSy;
	}

	public BigDecimal getCal70AgeSy() {
		return cal70AgeSy;
	}

	public void setCal70AgeSy(BigDecimal cal70AgeSy) {
		this.cal70AgeSy = cal70AgeSy;
	}

	public BigDecimal getCal80AgeSy() {
		return cal80AgeSy;
	}

	public void setCal80AgeSy(BigDecimal cal80AgeSy) {
		this.cal80AgeSy = cal80AgeSy;
	}

	public BigDecimal getCal90AgeSy() {
		return cal90AgeSy;
	}

	public void setCal90AgeSy(BigDecimal cal90AgeSy) {
		this.cal90AgeSy = cal90AgeSy;
	}
}