package cn.usst.market.mapper;

public interface DeleteReleaseResultMapper {
	
	public void deleteCompanyInvestByCompetitionIdQuarter(int competitionId, int quarter);
	public void deleteProductEfficiencyByCompetitionIdQuarter(int competitionId, int quarter);
	public void deleteProductMarketShareByCompetitionIdQuarter(int competitionId, int quarter);
	public void deleteCompanyMarketShareByCompanyIdQuarter(int companyId, int quarter);
	public void deleteCompanyQuarterTimeByCompetitionIdQuarter(int competitionId, int quarter);
	public void deleteBalanceScoreByCompanyIdQuarter(int companyId, int quarter);
	public void deleteFinancialRatioByCompanyIdQuarter(int companyId, int quarter);
	
	
}
