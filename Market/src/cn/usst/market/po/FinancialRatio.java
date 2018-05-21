package cn.usst.market.po;

public class FinancialRatio {
	private int id;
	private double quickRatio;//速动比率
	private double inventoryoverRatio;//库存周转率
	private double fixedassetoverRatio;//固定资产周转率
	private double totalassetRatio;//总资产率
	private double debtRatio;//负债比率
	private double grossprofitRatio;//毛利率
	private double netprofitRatio;//净利率
	private double returnRatio;//回爆率
	private int quarter;
	private int companyId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getQuickRatio() {
		return quickRatio;
	}
	public void setQuickRatio(double quickRatio) {
		this.quickRatio = quickRatio;
	}
	public double getInventoryoverRatio() {
		return inventoryoverRatio;
	}
	public void setInventoryoverRatio(double inventoryoverRatio) {
		this.inventoryoverRatio = inventoryoverRatio;
	}
	public double getFixedassetoverRatio() {
		return fixedassetoverRatio;
	}
	public void setFixedassetoverRatio(double fixedassetoverRatio) {
		this.fixedassetoverRatio = fixedassetoverRatio;
	}
	public double getTotalassetRatio() {
		return totalassetRatio;
	}
	public void setTotalassetRatio(double totalassetRatio) {
		this.totalassetRatio = totalassetRatio;
	}
	public double getDebtRatio() {
		return debtRatio;
	}
	public void setDebtRatio(double debtRatio) {
		this.debtRatio = debtRatio;
	}
	public double getGrossprofitRatio() {
		return grossprofitRatio;
	}
	public void setGrossprofitRatio(double grossprofitRatio) {
		this.grossprofitRatio = grossprofitRatio;
	}
	public double getNetprofitRatio() {
		return netprofitRatio;
	}
	public void setNetprofitRatio(double netprofitRatio) {
		this.netprofitRatio = netprofitRatio;
	}
	public double getReturnRatio() {
		return returnRatio;
	}
	public void setReturnRatio(double returnRatio) {
		this.returnRatio = returnRatio;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
}
