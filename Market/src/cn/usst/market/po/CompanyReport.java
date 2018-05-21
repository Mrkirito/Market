package cn.usst.market.po;

public class CompanyReport {
	private int id;
	private int companyId;
	private int quarter;
	private int buyReport;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public int getBuyReport() {
		return buyReport;
	}
	public void setBuyReport(int buyReport) {
		this.buyReport = buyReport;
	}
	
}
