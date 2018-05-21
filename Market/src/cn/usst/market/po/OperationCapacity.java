package cn.usst.market.po;

public class OperationCapacity {
	private Integer id;

	private Integer companyId;

	private Integer quarter;
	
	private Integer operateCapacity;

	private Integer workerProductivity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	

	public Integer getWorkerProductivity() {
		return workerProductivity;
	}

	public void setWorkerProductivity(Integer workerProductivity) {
		this.workerProductivity = workerProductivity;
	}

	
	public Integer getOperateCapacity() {
		return operateCapacity;
	}

	public void setOperateCapacity(Integer operateCapacity) {
		this.operateCapacity = operateCapacity;
	}

}
