package com.hangjia.bxj.shop.service.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.shop.dao.ShopGoodsPriceMapper;
import com.hangjia.bxj.shop.model.ShopGoodsPrice;
import com.hangjia.bxj.shop.query.ShopGoodsPriceQuery;
import com.hangjia.bxj.shop.service.ShopGoodsPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
@Service
public class ShopGoodsPriceServiceImpl implements ShopGoodsPriceService {
    @Autowired
    ShopGoodsPriceMapper priceMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(BaseCommonQuery query) {
        Result result = new Result();
        int count = priceMapper.selectByCount(query);
        List<ShopGoodsPrice> list = priceMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 分页查询
     * @return
     */
    public Result getPageListByGoodsId(ShopGoodsPriceQuery query) {
        Result result = new Result();
        int count = priceMapper.selectCountByGoodsId(query);
        List<ShopGoodsPrice> list = priceMapper.selectPageByGoodsId(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    public ShopGoodsPrice detail(Long id) {
        return priceMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加
     * @param price
     * @return
     */
    @MethodLog(remark = "添加商品数量价格")
    public int add(ShopGoodsPrice price) {
        return priceMapper.insertSelective(price);
    }

    /**
     * 更新
     * @param price
     * @return
     */
    @MethodLog(remark = "更新商品数量价格")
    public int update(ShopGoodsPrice price) {
        return priceMapper.updateByPrimaryKeySelective(price);
    }

    /**
     * 删除
     * @return
     */
    @MethodLog(remark = "删除商品数量价格")
    public int delete(Long id) {
        return priceMapper.deleteByPrimaryKey(id);
    }
}
