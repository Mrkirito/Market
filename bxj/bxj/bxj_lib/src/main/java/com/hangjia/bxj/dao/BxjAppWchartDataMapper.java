package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.BxjAppWchartData;
import com.hangjia.bxj.vo.ProductDetailVo;

public interface BxjAppWchartDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjAppWchartData record);

    int insertSelective(BxjAppWchartData record);

    BxjAppWchartData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjAppWchartData record);

    int updateByPrimaryKey(BxjAppWchartData record);
    
    List<BxjAppWchartData> getBxjAppWchartDatasByMonth(String date);
    List<BxjAppWchartData> getAllBxjAppWchartDatas();
    int getBxjAppWchartDatasByDate(String date);
    BxjAppWchartData  getAppWchartDatasByDate(String date);
    List<ProductDetailVo> selectTable();
}