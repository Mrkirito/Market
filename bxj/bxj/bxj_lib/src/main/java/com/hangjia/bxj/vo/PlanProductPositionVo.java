package com.hangjia.bxj.vo;

import com.hangjia.bxj.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class PlanProductPositionVo extends BaseModel implements Serializable {
	private Long fid;

	private String positionName;

	private Long gsId;

	private Boolean isEnable;

	private Integer sort;

	private Date ctime;

	private String ver;

	private static final long serialVersionUID = 1L;

	public PlanProductPositionVo(Long fid, String positionName, Long gsId, Boolean isEnable, Integer sort, Date ctime,
								 String ver) {
		this.fid = fid;
		this.positionName = positionName;
		this.gsId = gsId;
		this.isEnable = isEnable;
		this.sort = sort;
		this.ctime = ctime;
		this.ver = ver;
	}

	public PlanProductPositionVo() {
		super();
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName == null ? null : positionName.trim();
	}

	public Long getGsId() {
		return gsId;
	}

	public void setGsId(Long gsId) {
		this.gsId = gsId;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver == null ? null : ver.trim();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		PlanProductPositionVo other = (PlanProductPositionVo) that;
		return (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
				&& (this.getPositionName() == null ? other.getPositionName() == null
						: this.getPositionName().equals(other.getPositionName()))
				&& (this.getGsId() == null ? other.getGsId() == null : this.getGsId().equals(other.getGsId()))
				&& (this.getIsEnable() == null ? other.getIsEnable() == null
						: this.getIsEnable().equals(other.getIsEnable()))
				&& (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
				&& (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
				&& (this.getVer() == null ? other.getVer() == null : this.getVer().equals(other.getVer()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
		result = prime * result + ((getPositionName() == null) ? 0 : getPositionName().hashCode());
		result = prime * result + ((getGsId() == null) ? 0 : getGsId().hashCode());
		result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());
		result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
		result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
		result = prime * result + ((getVer() == null) ? 0 : getVer().hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", fid=").append(fid);
		sb.append(", positionName=").append(positionName);
		sb.append(", gsId=").append(gsId);
		sb.append(", isEnable=").append(isEnable);
		sb.append(", sort=").append(sort);
		sb.append(", ctime=").append(ctime);
		sb.append(", ver=").append(ver);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}