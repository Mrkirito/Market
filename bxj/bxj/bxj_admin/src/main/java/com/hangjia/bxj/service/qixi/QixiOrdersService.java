package com.hangjia.bxj.service.qixi;

import com.hangjia.bxj.model.qixi.QixiOrders;
import com.hangjia.bxj.query.qixi.OrdersQuery;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface QixiOrdersService {

    int getOrdersCount(OrdersQuery query);

    List<QixiOrders> getOrderssList(OrdersQuery query);

    int updateExpNo(String orderId, String expressNo);
}
