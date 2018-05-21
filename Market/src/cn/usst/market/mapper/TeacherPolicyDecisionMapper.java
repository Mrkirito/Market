package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.AdvertiseInfo;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyAdvertise;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyReport;
import cn.usst.market.po.CompanyRule;
import cn.usst.market.po.CompanyRuleInfo;
import cn.usst.market.po.CompanyStock;
import cn.usst.market.po.CompanyStrategy;
import cn.usst.market.po.Competition;
import cn.usst.market.po.CompetitionQuarterTime;
import cn.usst.market.po.FixedDeposit;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.MediaInfo;
import cn.usst.market.po.Member;
import cn.usst.market.po.MemberDutyCustom;
import cn.usst.market.po.OperationCapacity;
import cn.usst.market.po.PersonalGoal;
import cn.usst.market.po.PersonalGoalInfo;
import cn.usst.market.po.ProductInfo;
import cn.usst.market.po.ProductPrice;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.po.StrategyInfo;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.po.WorkersSalary;


public interface TeacherPolicyDecisionMapper {
	public List<MemberDutyCustom> findMemberDutyList(Integer id);
	
	public CompanyStrategy findCompanyGoalAndPolicy(IdQuarter idQuarter);
	
	public StrategyInfo findStrategyById(Integer id);
	
	public CompanyRule findCompanyRule(IdQuarter idQuarter);
	
	public CompanyRuleInfo findRuleInfoById(Integer id);
	
	//查找个人目标
	public PersonalGoal findPersonalGoalByMemberId(Integer id);
	public PersonalGoalInfo findPersonalGoalInfoById(Integer id);
	
	//查找设计的品牌
	public ProductInfo findProductInfoById(Integer id);
	public List<CompanyProduct> findProductsByCompanyIdQuarter(int companyId, int quarter);
	
	//找最低价、最高价、平均价格
	public int findMinPriceProduct(IdQuarter idQuarter);
	public int findMaxPriceProduct(IdQuarter idQuarter);
	public int findAvgPriceProduct(IdQuarter idQuarter);
	
	//查找广告
	public List<CompanyAdvertise> findAdvertiseByCompanyIdQuarter(int companyId, int quarter);
	public CompanyAdvertise findAdvertiseByProductIdQuarter(int productId, int quarter);
	//广告详细信息
	public AdvertiseInfo findAdvertiseInfoById(int id);
	//根据ID找公司设计产品
	public CompanyProduct findProductById(int id);
	
	//媒体投放数量
	public int findMediaNumByIdQuarter(IdQuarter idQuarter);
	
	//从媒体投放中找产品id
	public List<Integer> findProductIdFromMedia(IdQuarter idQuarter);
	
	//找所有媒体
	public List<MediaInfo> findAllMediaInfo();
	public List<CompanyMedia> findCompanyMediaByIdQuarterPid(int id, int quarter, int pid);
	public List<CompanyMedia> findMediabyCompanyIdQuarter(IdQuarter idQuarter);
	public MediaInfo findMediaInfoById(int id);
	//找产品定价
	public List<Integer> findProductIdFromProdPrice(IdQuarter idQuarter);
	public ProductPrice findProductSalePriceByIdQPid(int id, int quarter, int pid);
	
	//查找市场调研报告
	public CompanyReport findComReportByIdQuarter(IdQuarter idQuarter);
	
	//找开始实体市场
	public CompanyMarket findCompanyPhyMarketByIdQuarter(IdQuarter idQuarter);
	//找开设网络市场
	public CompanyMarket findCompanyNetMarketByIdQuarter(IdQuarter idQuarter);
	
	//找市场具体信息
	public MarketInfo findMarketInfoById(Integer id);
	
	//销售人数
	public List<HirePeople> findHirePeopleByCompanyIdQuarter(int companyId, int quarter);
	public List<HirePeopleOnline> findHirePeopleOnlineByCompanyIdQuarter(int companyId, int quarter);
	
	//销售人员薪酬
	public SalesSalary findSalesSalaryByIdQuarter(IdQuarter idQuarter);
	//工厂工人薪酬
	public WorkersSalary findWorkersSalaryByIdQuarter(IdQuarter idQuarter);
	
	
	//查找公司产能
	public CompanyCapacity findCompanyCapacityByIdQuarter(IdQuarter idQuarter);
	public CompanyCapacity findCompanyCapacityByCompanyIdQuarter(int companyId, int quarter);
	
	public OperationCapacity findOpeartionCapacityByCompanyIdQuarter(int companyId, int quarter);
	
	//找季度增加的产能
	//public int findAddCapacityByIdQuarter(IdQuarter idQuarter);
	
	//找前n季度固定产能之和
	//public int findTotalCapacityByIdQuarter(IdQuarter idQuarter);
	
	//查找公司持股
	public List<CompanyStock> findCompanyStockByIdQuarter(IdQuarter idQuarter);
	
	//查找公司定期存款
	public FixedDeposit findCompanyFixedDepositByIdQuarter(IdQuarter idQuarter);
	
	//产品预测需求量
	public int findForecastDemandByProductIdQuarter(int productId, int quarter);
	
	//产品库存量
	public int findProductInventoryByProductIdQuarter(int productId, int quarter);
	
}
