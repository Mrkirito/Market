package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.BxjBbAppWchartData;
import com.hangjia.bxj.vo.ProductDetailVo;

import java.util.List;

public interface BxjBbAppWchartDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjBbAppWchartData record);

    int insertSelective(BxjBbAppWchartData record);

    BxjBbAppWchartData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjBbAppWchartData record);

    int updateByPrimaryKey(BxjBbAppWchartData record);
    
    List<BxjBbAppWchartData> getBxjAppWchartDatasByMonth(String date);
    List<BxjBbAppWchartData> getAllBxjAppWchartDatas();
    int getBxjAppWchartDatasByDate(String date);
    BxjBbAppWchartData  getAppWchartDatasByDate(String date);
    List<ProductDetailVo> selectTable();
}