package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.CompanyInvestment;
import cn.usst.market.po.CompanyLoan;
import cn.usst.market.po.CompanyMarketShare;
import cn.usst.market.po.PerformanceReport;

public interface PerformanceReportMapper {
	public List<PerformanceReport> findCompanyMarketShareByCompetetionId(int competetionId, int quarter);
	public List<PerformanceReport> findCompanyProductMarketShareByCompetionId(int competitionId, int quarter);
	public CompanyMarketShare findCompanyMarketShareByCompanyId(int companyId, int quarter);
	public CompanyInvestment findCompanyInvestmentByCompanyId(int companyId, int quarter);
	public CompanyLoan findCompanyLoanByCompanyIdQuarter(int companyId, int quarter);
}
