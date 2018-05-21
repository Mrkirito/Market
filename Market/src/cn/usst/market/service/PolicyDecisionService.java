package cn.usst.market.service;

import java.util.List;

import cn.usst.market.po.AdvertiseInfo;
import cn.usst.market.po.CompanyAdvertise;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyReport;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.MediaInfo;
import cn.usst.market.po.OperationCapacity;
import cn.usst.market.po.ProductPrice;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.po.WorkersSalary;

public interface PolicyDecisionService {
	
	
	//找产品最小价、最高价、平均价
	public int findMinPriceProduct(IdQuarter idQuarter);
	public int findMaxPriceProduct(IdQuarter idQuarter);
	public int findAvgPriceProduct(IdQuarter idQuarter);
	
	//找公司广告
	public List<CompanyAdvertise> findAdvertiseByCompanyIdQuarter(int companyId, int quarter);
	CompanyAdvertise findAdvertiseByProductIdQuarter(int productId, int quarter);
	
	//找广告详细信息
	public AdvertiseInfo findAdvertiseInfoById(int id);
	
	//媒体投放数量
	int findMediaNumByIdQuarter(IdQuarter idQuarter);
	
	//销售人员薪酬
	public SalesSalary findSalesSalaryByIdQuarter(IdQuarter idQuarter);
	public WorkersSalary findWorkersSalaryByIdQuarter(IdQuarter idQuarter);
	
	//根据ID找产品
	public CompanyProduct findProductById(int id);
	
	//找各个产品的媒体投放
	List<Integer> findProductIdFromMedia(IdQuarter idQuarter);
	public List<MediaInfo> findAllMediaInfo();
	List<CompanyMedia> findCompanyMediaByIdQuarterPid(int companyId, int quarter, int pid);
	List<Integer> findProductIdFromProdPrice(IdQuarter idQuarter);
	ProductPrice findProductSalePriceByIdQPid(int id, int quarter, int pid);
	CompanyReport findComReportByIdQuarter(IdQuarter idQuarter);
	
	List<CompanyProduct> findProductsByCompanyIdQuarter(int companyId, int quarter);
	List<CompanyMedia> findMediabyCompanyIdQuarter(IdQuarter idQuarter);
	MediaInfo findMediaInfoById(int id);
	
	//找市场的详细信息
	MarketInfo findMarketInfoById(Integer id);
	
	//公司产能
	CompanyCapacity findCompanyCapacityByIdQuarter(IdQuarter idQuarter);
	public CompanyCapacity findCompanyCapacityByCompanyIdQuarter(int companyId, int quarter);
	
	OperationCapacity findOpeartionCapacityByCompanyIdQuarter(int companyId, int quarter);
	
	//公司雇佣销售人员
	List<HirePeople> findHirePeopleByCompanyIdQuarter(int companyId, int quarter);
	List<HirePeopleOnline> findHirePeopleOnlineByCompanyIdQuarter(int companyId, int quarter);
	
	//产品预测需求量
	int findForecastDemandByProductIdQuarter(int productId, int quarter);
	
	//产品库存设置
	int findProductInventoryByProductIdQuarter(int productId, int quarter);
	
}
