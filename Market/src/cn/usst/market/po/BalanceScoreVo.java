package cn.usst.market.po;

public class BalanceScoreVo {
	private BalanceScore balanceScore;
	private Company company;
	private BalanceScoreMinData balanceScoreMinData;
	private BalanceScoreMaxData balanceScoreMaxData;
	private BalanceScoreAvgData balanceScoreAvgData;
	
	public BalanceScore getBalanceScore() {
		return balanceScore;
	}
	public void setBalanceScore(BalanceScore balanceScore) {
		this.balanceScore = balanceScore;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public BalanceScoreMinData getBalanceScoreMinData() {
		return balanceScoreMinData;
	}
	public void setBalanceScoreMinData(BalanceScoreMinData balanceScoreMinData) {
		this.balanceScoreMinData = balanceScoreMinData;
	}
	public BalanceScoreMaxData getBalanceScoreMaxData() {
		return balanceScoreMaxData;
	}
	public void setBalanceScoreMaxData(BalanceScoreMaxData balanceScoreMaxData) {
		this.balanceScoreMaxData = balanceScoreMaxData;
	}
	public BalanceScoreAvgData getBalanceScoreAvgData() {
		return balanceScoreAvgData;
	}
	public void setBalanceScoreAvgData(BalanceScoreAvgData balanceScoreAvgData) {
		this.balanceScoreAvgData = balanceScoreAvgData;
	}
	
	
}
