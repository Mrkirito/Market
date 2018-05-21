package com.hangjia.bxj.dao.qixi;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.qixi.QixiGoods;

import java.util.List;

public interface QixiGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QixiGoods record);

    int insertSelective(QixiGoods record);

    QixiGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QixiGoods record);

    int updateByPrimaryKey(QixiGoods record);

    /**************************** extend begin here *******************************/
    int selectGoodsCount();

    List<QixiGoods> selectGoodsList(BaseCommonQuery query);
}