package cn.usst.market.po;

public class CompanyFinanceVo {
	private Company company;
	
	private BalanceSheet balanceSheet;
	
	private CashFlow cashFlow;
	
	private IncomeStatement incomeStatement;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public BalanceSheet getBalanceSheet() {
		return balanceSheet;
	}

	public void setBalanceSheet(BalanceSheet balanceSheet) {
		this.balanceSheet = balanceSheet;
	}

	public CashFlow getCashFlow() {
		return cashFlow;
	}

	public void setCashFlow(CashFlow cashFlow) {
		this.cashFlow = cashFlow;
	}

	public IncomeStatement getIncomeStatement() {
		return incomeStatement;
	}

	public void setIncomeStatement(IncomeStatement incomeStatement) {
		this.incomeStatement = incomeStatement;
	}
	
	

}
