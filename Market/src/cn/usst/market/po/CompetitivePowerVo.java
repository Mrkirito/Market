package cn.usst.market.po;



public class CompetitivePowerVo {
	private Company company;
	private Integer capacityNow ;
	 private Integer capacityAdd ;
	
	 private Integer  operateCapacity;
	 private double workerEfficiency;
	
	
	public Integer getCapacityNow() {
		return capacityNow;
	}
	public void setCapacityNow(Integer capacityNow) {
		this.capacityNow = capacityNow;
	}
	
	public Integer getCapacityAdd() {
		return capacityAdd;
	}
	public void setCapacityAdd(Integer capacityAdd) {
		this.capacityAdd = capacityAdd;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Integer getOperateCapacity() {
		return operateCapacity;
	}
	public void setOperateCapacity(Integer operateCapacity) {
		this.operateCapacity = operateCapacity;
	}
	public double getWorkerEfficiency() {
		return workerEfficiency;
	}
	public void setWorkerEfficiency(double workerEfficiency) {
		this.workerEfficiency = workerEfficiency;
	}
	
	
	
	
	}