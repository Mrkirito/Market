package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.CompanyDecision;
import cn.usst.market.po.CompanyDecitionZLYPo;
import cn.usst.market.po.GlobalPathNeedsVo;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.MarketOpenedPo;
import cn.usst.market.po.ProductMarketShare;
import cn.usst.market.po.PysicalEmploeePo;
import cn.usst.market.po.SaleIncomVo;
import cn.usst.market.po.SallSituationVo;
import cn.usst.market.po.StoreInforVo;

public interface SallSituationMapper {
	
	void CompanyDecitionLog(CompanyDecitionZLYPo po);
	
	void DelCompanyDecitionLog(CompanyDecitionZLYPo po);

	List<SallSituationVo> selectSaleSituationbyCompanyID(PysicalEmploeePo po);
	
	List<SaleIncomVo> selectPathAbilitybyCompanyID(PysicalEmploeePo po);

	List<GlobalPathNeedsVo> selectGlobalPathSharebycompanyid(PysicalEmploeePo po);
	
	List<StoreInforVo> selectStoreInforbycompanyid(PysicalEmploeePo po);

	Integer selectMoneySum(Integer companyid);

	Integer selectMoneybyDecitionIDandCompanyID(CompanyDecision de);//修改
	
	Integer selectMoneybyDecitionID(Integer de);
	
	Integer countMoneybyDecitionID(Integer de);
	
	List<HirePeople> selectHirePeoplebyCompanyID(PysicalEmploeePo po);

	void deletHirePeoplebyCompanyID(PysicalEmploeePo po);
	
	void updateHirePeoplebyCompanyID(HirePeople po);
	
	void insertHirePeoplebyCompanyID(HirePeople hp);

	Integer countWhetherMarketOpened(MarketOpenedPo po);
	
	MarketOpenedPo selectOpenedMarket(MarketOpenedPo po);
	
	void deletOpenedMarket(MarketOpenedPo po);
	
	Integer insertOpenedMarket(MarketOpenedPo po);
	
}
