package com.hangjia.bxj.dao.qixi;

import com.hangjia.bxj.model.qixi.QixiOrdersDetail;

public interface QixiOrdersDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QixiOrdersDetail record);

    int insertSelective(QixiOrdersDetail record);

    QixiOrdersDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QixiOrdersDetail record);

    int updateByPrimaryKey(QixiOrdersDetail record);

    /**************************** extend begin here *******************************/
    /** 更新快递号 **/
    int updateExpNo(QixiOrdersDetail record);
}