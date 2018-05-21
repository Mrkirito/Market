package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.PlanProductMain;
import com.hangjia.bxj.model.PlanProductMainWithBLOBs;
import com.hangjia.bxj.query.ProductQuery;

public interface PlanProductMainMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(PlanProductMainWithBLOBs record);

    int insertSelective(PlanProductMainWithBLOBs record);

    PlanProductMainWithBLOBs selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(PlanProductMainWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PlanProductMainWithBLOBs record);

    int updateByPrimaryKey(PlanProductMain record);
    
    int queryPageDataCount(ProductQuery query);
    
    List<PlanProductMain> queryPageData(ProductQuery query);
    
    int queryPlanBookStatisticsCount(ProductQuery query);
    
    List<ProductQuery> queryPlanBookStatistics(ProductQuery query);
    
    List<ProductQuery> queryUserStatistics(ProductQuery query);
    
    
    List<ProductQuery> queryMultipProductRulesByLqnl(Integer fid);
    
    List<ProductQuery> queryMultipProductRulesByBxnx(Integer fid);
    
    List<ProductQuery> querySingleProductRules(Integer fid);
}