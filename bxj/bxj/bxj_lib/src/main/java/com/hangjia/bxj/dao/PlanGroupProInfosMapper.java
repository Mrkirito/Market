package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.PlanGroupProInfos;
import com.hangjia.bxj.query.ProductQuery;

public interface PlanGroupProInfosMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(PlanGroupProInfos record);

    int insertSelective(PlanGroupProInfos record);

    PlanGroupProInfos selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(PlanGroupProInfos record);

    int updateByPrimaryKey(PlanGroupProInfos record);
    
    int getQueryCount(ProductQuery query);
    List<PlanGroupProInfos> getQueryData(ProductQuery query);
}