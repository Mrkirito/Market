package com.hangjia.bxj.dao.qixi;

import com.hangjia.bxj.model.qixi.QixiOrders;
import com.hangjia.bxj.query.qixi.OrdersQuery;

import java.util.List;

public interface QixiOrdersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QixiOrders record);

    int insertSelective(QixiOrders record);

    QixiOrders selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QixiOrders record);

    int updateByPrimaryKey(QixiOrders record);

    /**************************** extend begin here *******************************/
    /** 总个数 **/
    int selectOrdersCount(OrdersQuery query);

    /** 分页列表 **/
    List<QixiOrders> selectOrdersList(OrdersQuery query);
}