package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.TeacherPolicyDecisionMapper;
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
import cn.usst.market.service.PolicyDecisionService;

@Service("policyDecisionService")
public class PolicyDecisionServiceImpl implements PolicyDecisionService {
	@Autowired
	private TeacherPolicyDecisionMapper teacherPolicyDecisionMapper;
	//找公司设计的产品
	@Override
	public List<CompanyProduct> findProductsByCompanyIdQuarter(int companyId,int quarter){
		return teacherPolicyDecisionMapper.findProductsByCompanyIdQuarter(companyId,quarter);
	}
	
	//找最低价、最高价、平均价
	@Override
	public int findMinPriceProduct(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findMinPriceProduct(idQuarter);
	}
	@Override
	public int findMaxPriceProduct(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findMaxPriceProduct(idQuarter);
	}
	@Override
	public int findAvgPriceProduct(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findAvgPriceProduct(idQuarter);
	}
	
	//找公司广告
	@Override
	public List<CompanyAdvertise> findAdvertiseByCompanyIdQuarter(int companyId,int quarter){
		return teacherPolicyDecisionMapper.findAdvertiseByCompanyIdQuarter(companyId,quarter);
	}
	@Override
	public CompanyAdvertise findAdvertiseByProductIdQuarter(int productId,int quarter){
		return teacherPolicyDecisionMapper.findAdvertiseByProductIdQuarter(productId, quarter);
	}
	
	//广告详细信息
	@Override
	public AdvertiseInfo findAdvertiseInfoById(int id){
		return teacherPolicyDecisionMapper.findAdvertiseInfoById(id);
	}
	
	//根据ID找公司设计产品
	@Override
	public CompanyProduct findProductById(int id){
		return teacherPolicyDecisionMapper.findProductById(id);
	}
	
	//媒体投放数量
	@Override
	public int findMediaNumByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findMediaNumByIdQuarter(idQuarter);
	}
	
	//从媒体投放中找产品id
	@Override
	public List<Integer> findProductIdFromMedia(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findProductIdFromMedia(idQuarter);
	}
	//找所有媒体
	@Override
	public List<MediaInfo> findAllMediaInfo(){
		return teacherPolicyDecisionMapper.findAllMediaInfo();
	}
	//根据ID找媒体详细信息
	@Override
	public MediaInfo findMediaInfoById(int id){
		return teacherPolicyDecisionMapper.findMediaInfoById(id);
	}
	
	//找这个产品的投放量
	@Override
	public List<CompanyMedia> findCompanyMediaByIdQuarterPid(int companyId,int quarter,int pid){
		return teacherPolicyDecisionMapper.findCompanyMediaByIdQuarterPid(companyId, quarter, pid);
	}
	
	//找公司下面的媒体投放量
	@Override
	public List<CompanyMedia> findMediabyCompanyIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findMediabyCompanyIdQuarter(idQuarter);
	}
	
	//产品定价
	@Override
	public List<Integer> findProductIdFromProdPrice(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findProductIdFromMedia(idQuarter);
	}
	@Override
	public ProductPrice findProductSalePriceByIdQPid(int id,int quarter,int pid){
		return teacherPolicyDecisionMapper.findProductSalePriceByIdQPid(id, quarter, pid);
	}
	
	//市场调研报告
	@Override
	public CompanyReport findComReportByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findComReportByIdQuarter(idQuarter);
	}
	
	//销售人数
	@Override
	public List<HirePeople> findHirePeopleByCompanyIdQuarter(int companyId,int quarter){
		return teacherPolicyDecisionMapper.findHirePeopleByCompanyIdQuarter(companyId, quarter);
	}
	@Override
	public List<HirePeopleOnline> findHirePeopleOnlineByCompanyIdQuarter(int companyId,int quarter){
		return teacherPolicyDecisionMapper.findHirePeopleOnlineByCompanyIdQuarter(companyId, quarter);
	}
	
	//销售人员薪酬
	@Override
	public SalesSalary findSalesSalaryByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findSalesSalaryByIdQuarter(idQuarter);
	}
	//工厂工人薪酬
	@Override
	public WorkersSalary findWorkersSalaryByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findWorkersSalaryByIdQuarter(idQuarter);
	}
	
	//找市场具体信息
	@Override
	public MarketInfo findMarketInfoById(Integer id){
		return teacherPolicyDecisionMapper.findMarketInfoById(id);
	}
	
	//查找公司产能
	@Override
	public CompanyCapacity findCompanyCapacityByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyCapacityByIdQuarter(idQuarter);
	}
	@Override
	public CompanyCapacity findCompanyCapacityByCompanyIdQuarter(int companyId,int quarter){
		return teacherPolicyDecisionMapper.findCompanyCapacityByCompanyIdQuarter(companyId, quarter);
	}
	
	
	@Override
	public OperationCapacity findOpeartionCapacityByCompanyIdQuarter(int companyId,int quarter){
		return teacherPolicyDecisionMapper.findOpeartionCapacityByCompanyIdQuarter(companyId, quarter);
	}
	
	//产品预测需求量
	@Override
	public int findForecastDemandByProductIdQuarter(int productId,int quarter){
		return teacherPolicyDecisionMapper.findForecastDemandByProductIdQuarter(productId, quarter);
	}
	
	//产品库存量
	@Override
	public int findProductInventoryByProductIdQuarter(int productId,int quarter){
		return teacherPolicyDecisionMapper.findProductInventoryByProductIdQuarter(productId, quarter);
	}

}
