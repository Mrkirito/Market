package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.BalanceScore;
import cn.usst.market.po.BalanceScoreAvgData;
import cn.usst.market.po.BalanceScoreMaxData;
import cn.usst.market.po.BalanceScoreMinData;
import cn.usst.market.po.BalanceScoreVo;
import cn.usst.market.po.CompanyMediaVo;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.LearnTime;
import cn.usst.market.po.LearnTimeVo;
import cn.usst.market.po.SalesSalary;

public interface BalanceScoreMapper {
	public BalanceScoreVo loadBalanceScore(BalanceScore balanceScore);
	public BalanceScoreMinData getMinData(int id, int quarter);
	public BalanceScoreMaxData getMaxData(int id, int quarter);
	public BalanceScoreAvgData getAvgData(int id, int quarter);
	public List<BalanceScoreVo> loadAllBalanceScore(BalanceScoreVo balanceScoreVo);
	public void insertBalanceScore(BalanceScore balanceScore);
	public void insertLearnTime(LearnTime learnTime);
	public void updateLearnTime(LearnTime learnTime);
	public LearnTime getLearnTimeByMemberId(LearnTime learnTime);
	public List<LearnTime> getLearnTimeByCompanyId(LearnTimeVo learnTimeVo);
	public List<CompanyMediaVo> getCompanyMediaCostByCompanyId(CompanyMediaVo companyMediaVo);
	public SalesSalary getSalesSalaryByCompanyIdQuarter(int companyId, int quarter);
	public List<HirePeople> getHirePeopleByCompanyIdQuarter(int companyId);
	public List<HirePeopleOnline> getHirePeopleOnlineByCompanyIdQuarter(int companyId);
}
