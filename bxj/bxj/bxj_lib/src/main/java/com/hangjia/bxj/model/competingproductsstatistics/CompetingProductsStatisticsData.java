package com.hangjia.bxj.model.competingproductsstatistics;

import java.io.Serializable;
import java.util.Date;

public class CompetingProductsStatisticsData implements Serializable {
    private Long id;

    private Date dataTime;

    private Integer bxsCount;

    private Integer bxzsCount;

    private Integer bxrCount;

    private Integer ssbdCount;
    
    private Integer bxjCount;
    private Integer jmCount;
    private Integer jjbxCount;
    private Integer bxdkCount;
    private Integer iybCount;
    private Integer bxwwCount;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    

    public Integer getBxsCount() {
		return bxsCount;
	}

	public void setBxsCount(Integer bxsCount) {
		this.bxsCount = bxsCount;
	}

	public Integer getBxzsCount() {
		return bxzsCount;
	}

	public void setBxzsCount(Integer bxzsCount) {
		this.bxzsCount = bxzsCount;
	}

	public Integer getBxrCount() {
		return bxrCount;
	}

	public void setBxrCount(Integer bxrCount) {
		this.bxrCount = bxrCount;
	}

	public Integer getSsbdCount() {
		return ssbdCount;
	}

	public void setSsbdCount(Integer ssbdCount) {
		this.ssbdCount = ssbdCount;
	}

	public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    

    public Integer getBxjCount() {
		return bxjCount;
	}

	public void setBxjCount(Integer bxjCount) {
		this.bxjCount = bxjCount;
	}

	public Integer getJmCount() {
		return jmCount;
	}

	public void setJmCount(Integer jmCount) {
		this.jmCount = jmCount;
	}

	public Integer getJjbxCount() {
		return jjbxCount;
	}

	public void setJjbxCount(Integer jjbxCount) {
		this.jjbxCount = jjbxCount;
	}

	public Integer getBxdkCount() {
		return bxdkCount;
	}

	public void setBxdkCount(Integer bxdkCount) {
		this.bxdkCount = bxdkCount;
	}

	public Integer getIybCount() {
		return iybCount;
	}

	public void setIybCount(Integer iybCount) {
		this.iybCount = iybCount;
	}

	public Integer getBxwwCount() {
		return bxwwCount;
	}

	public void setBxwwCount(Integer bxwwCount) {
		this.bxwwCount = bxwwCount;
	}

}