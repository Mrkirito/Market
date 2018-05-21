package com.hangjia.bxj.service.qixi.impl;

import com.hangjia.bxj.dao.qixi.QixiOrdersDetailMapper;
import com.hangjia.bxj.dao.qixi.QixiOrdersMapper;
import com.hangjia.bxj.model.qixi.QixiOrders;
import com.hangjia.bxj.model.qixi.QixiOrdersDetail;
import com.hangjia.bxj.query.qixi.OrdersQuery;
import com.hangjia.bxj.service.qixi.QixiOrdersService;
import com.hangjia.bxj.ucenter.dao.UcUserMapper;
import com.hangjia.bxj.ucenter.model.UcUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
@Service
public class QixiOrdersServiceImpl implements QixiOrdersService {
    @Autowired
    QixiOrdersMapper ordersMapper;
    @Autowired
    UcUserMapper userMapper;
    @Autowired
    QixiOrdersDetailMapper ordersDetailMapper;

    /**
     * 总个数
     * @return
     */
    public int getOrdersCount(OrdersQuery query) {
        return ordersMapper.selectOrdersCount(query);
    }

    /**
     * 分页列表
     * @return
     */
    public List<QixiOrders> getOrderssList(OrdersQuery query) {
        if(StringUtils.isBlank(query.getOrderBy())) query.setOrderBy("t1.id desc");
        List<QixiOrders> list = ordersMapper.selectOrdersList(query);
        for(QixiOrders orders : list) {
            UcUser user = userMapper.selectByPrimaryKey(orders.getUserId());
            if(null != user) orders.setRegistPhone(user.getUsername());
        }
        return list;
    }

    /**
     * 更新快递号
     * @param orderId
     * @param expressNo
     * @return
     */
    public int updateExpNo(String orderId, String expressNo) {
        QixiOrdersDetail ordersDetail = new QixiOrdersDetail();
        ordersDetail.setOrderId(orderId);
        ordersDetail.setExpressNo(expressNo);
        return ordersDetailMapper.updateExpNo(ordersDetail);
    }
}
