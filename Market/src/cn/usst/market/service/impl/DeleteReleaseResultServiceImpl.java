package cn.usst.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.DeleteReleaseResultMapper;
import cn.usst.market.service.DeleteReleaseResultService;

@Service("deleteReleaseResultService")
public class DeleteReleaseResultServiceImpl implements DeleteReleaseResultService {
	@Autowired
	private DeleteReleaseResultMapper deleteReleaseResultMapper;
	
	@Override
	public void deleteCompanyInvestByCompetitionIdQuarter(int competitionId, int quarter){
		deleteReleaseResultMapper.deleteCompanyInvestByCompetitionIdQuarter(competitionId, quarter);
	}
	
	@Override
	public void deleteProductEfficiencyByCompetitionIdQuarter(int competitionId, int quarter){
		deleteReleaseResultMapper.deleteProductEfficiencyByCompetitionIdQuarter(competitionId, quarter);
	}
	
	@Override
	public void deleteProductMarketShareByCompetitionIdQuarter(int competitionId, int quarter){
		deleteReleaseResultMapper.deleteProductMarketShareByCompetitionIdQuarter(competitionId, quarter);
	}
	
	@Override
	public void deleteCompanyMarketShareByCompanyIdQuarter(int companyId, int quarter){
		deleteReleaseResultMapper.deleteCompanyMarketShareByCompanyIdQuarter(companyId, quarter);
	}
	
	@Override
	public void deleteCompanyQuarterTimeByCompetitionIdQuarter(int competitionId, int quarter){
		deleteReleaseResultMapper.deleteCompanyQuarterTimeByCompetitionIdQuarter(competitionId, quarter);
	}
	
	@Override
	public void deleteBalanceScoreByCompanyIdQuarter(int companyId, int quarter){
		deleteReleaseResultMapper.deleteBalanceScoreByCompanyIdQuarter(companyId, quarter);
	}
	
	@Override
	public void deleteFinancialRatioByCompanyIdQuarter(int companyId, int quarter){
		deleteReleaseResultMapper.deleteFinancialRatioByCompanyIdQuarter(companyId, quarter);
	}
}
