/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.shop.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.shop.ShopOrders;
import com.hangjia.bxj.model.shop.ShopOrdersDetail;
import com.hangjia.bxj.query.shop.ShopOrdersQuery;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Caigp
 * Date: 2016/11/2 10:48
 */
public interface ShopOrdersService {

    /**
     * 分页查询
     * @return
     */
    Result getPageList(BaseCommonQuery query);


    /**
     * 分页查询
     * @return
     */
    Result getPageListByQuery(ShopOrdersQuery query);


    ShopOrders selectByPrimaryKey(Long id);


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
    List<ShopOrders> selectByPage(ShopOrdersQuery query);
    
    /**
     * 导入功能
     * @param file
     * @return
     */
    Map<String,Object> importExcel(MultipartFile file);
    

    /***
     * 20161115新版商城列表
     * @param query
     * @return
     */
    Result getShopOrderList(ShopOrdersQuery query);
    int getShopOrderListCount(ShopOrdersQuery query);
    List<ShopOrdersDetail> getShopOrderListByExport(ShopOrdersQuery query);
}
