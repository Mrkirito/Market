package cn.usst.market.service;

public interface DeleteReleaseResultService {

	void deleteCompanyInvestByCompetitionIdQuarter(int competitionId, int quarter);

	void deleteProductEfficiencyByCompetitionIdQuarter(int competitionId, int quarter);

	void deleteProductMarketShareByCompetitionIdQuarter(int competitionId, int quarter);

	void deleteCompanyMarketShareByCompanyIdQuarter(int companyId, int quarter);

	void deleteCompanyQuarterTimeByCompetitionIdQuarter(int competitionId, int quarter);

	void deleteBalanceScoreByCompanyIdQuarter(int companyId, int quarter);

	void deleteFinancialRatioByCompanyIdQuarter(int companyId, int quarter);
	

}
