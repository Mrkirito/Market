package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.BxjAppWchartData;
import com.hangjia.bxj.model.BxjHjAppWchartData;
import com.hangjia.bxj.vo.ProductDetailVo;

import java.util.List;

public interface BxjHjAppWchartDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjHjAppWchartData record);

    int insertSelective(BxjHjAppWchartData record);

    BxjHjAppWchartData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjHjAppWchartData record);

    int updateByPrimaryKey(BxjHjAppWchartData record);
    
    List<BxjHjAppWchartData> getBxjAppWchartDatasByMonth(String date);
    List<BxjHjAppWchartData> getAllBxjAppWchartDatas();
    int getBxjAppWchartDatasByDate(String date);
    BxjHjAppWchartData  getAppWchartDatasByDate(String date);
    List<ProductDetailVo> selectTable();
}