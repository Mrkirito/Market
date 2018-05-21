package com.hangjia.bxj.dao.shop;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.shop.ShopOrders;
import com.hangjia.bxj.model.shop.ShopOrdersDetail;
import com.hangjia.bxj.query.shop.ShopOrdersQuery;

import java.util.List;

public interface ShopOrdersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopOrders record);

    int insertSelective(ShopOrders record);

    ShopOrders selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopOrders record);

    int updateByPrimaryKey(ShopOrders record);


    /**************************** extend begin here *******************************/
    /**
     * 总个数
     * @param query
     * @return
     */
    int selectByCount(BaseCommonQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<ShopOrders> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByQuery(ShopOrdersQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<ShopOrders> selectPageByQuery(ShopOrdersQuery query);
    
    
    /***
     * 20161115新版商城数量
     * @param query
     * @return
     */
    int getShopOrderListCount(ShopOrdersQuery query);
    /**
     * 20161115新版商城列表
     * @param query
     * @return
     */
    List<ShopOrdersDetail> getShopOrderList(ShopOrdersQuery query);
    /**
     * 商城总和
     * @param query
     * @return
     */
    ShopOrdersDetail getSumShopOrder(ShopOrdersQuery query);


}