package cn.usst.market.mapper;

import cn.usst.market.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 陈立阳 on 2017/9/24.
 */
public interface FinalCheckMapper {

    Company selectCompanyById(int companyId);

    StrategyPojo selectStrategyByCompanyId(int companyId, int quarter);

    List<PositionPojo> selectPositionByCompanyId(int companyId);

    RulePojo selectRulesByCompanyId(int companyId, int quarter);

    List<MarketPojo> selectMarketInfoByCompanyId(int companyId, int quarter);

    List<ProductPojo> selectProductByCompanyId(int companyId, int quarter);

    CapacityPojo selectCapacityByCompanyId(int companyId, int quarter);

    StockPojo selectStockByCompanyId(int companyId, int quarter);

    DepositPojo selectDepositByCompanyId(int companyId, int quarter);

    void updateCompanyQuaterTime(@Param("comId") int companyId, @Param("curTime") String currentTime);

    List<ProductPrice> selectProductByCompanyIdAndQuarter(int companyId, int quarter);

    SalaryDO selectSalary(int companyId, int quarter);

    List<CompanyMarket> selectCompanyMarketByCompanyIdAndQuarter(int companyId, int quarter);

    List<HirePeople> selectHirePeopleByCompanyIdAndQuarter(int companyId, int quarter);

    HirePeopleOnline selectHirePeopleOnlineByCompanyIdAndQuarter(int companyId, int quarter);

    String selectMarketOpened();
}
