package cn.usst.market.mapper;

import cn.usst.market.po.FinancialRatio;
import cn.usst.market.po.FinancialRatioAvg;
import cn.usst.market.po.FinancialRatioMax;
import cn.usst.market.po.FinancialRatioMin;
import cn.usst.market.po.FinancialRatioVo;

public interface FinancialRatioMapper {
	public FinancialRatioVo findFinancialRatioByCompanyId(int companyId, int quarter);
	public FinancialRatioMax getMax(int id, int quarter);
	public FinancialRatioMin getMin(int id, int quarter);
	public FinancialRatioAvg getAvg(int id, int quarter);
	public void insertFinancialRatio(FinancialRatio financialRatio);
}
