package com.hangjia.bxj.shop.service.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.shop.dao.ShopGoodsMapper;
import com.hangjia.bxj.shop.model.ShopGoods;
import com.hangjia.bxj.shop.query.ShopGoodsQuery;
import com.hangjia.bxj.shop.service.ShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
@Service
public class ShopGoodsServiceImpl implements ShopGoodsService {
    @Value("${show_path}")
    private String path;
    @Autowired
    ShopGoodsMapper shopGoodsMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(BaseCommonQuery query) {
        Result result = new Result();
        int count = shopGoodsMapper.selectByCount(query);
        List<ShopGoods> list = shopGoodsMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 分页查询
     * @return
     */
    public Result getPageListByQuery(ShopGoodsQuery query) {
        Result result = new Result();
        int count = shopGoodsMapper.selectCountByQuery(query);
        List<ShopGoods> list = shopGoodsMapper.selectPageByQuery(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    public ShopGoods detail(Long id) {
        ShopGoods goods = shopGoodsMapper.selectByPrimaryKey(id);
        goods.setPicturePath(path);
        goods.setSharePicPath(path);
        goods.setPicture2Path(path);
        return goods;
    }

    /**
     * 添加
     * @param goods
     * @return
     */
    @MethodLog(remark = "添加商品")
    public int add(ShopGoods goods) {
        goods.setDescription(goods.getDescription().replaceAll("white-space", ""));
        return shopGoodsMapper.insertSelective(goods);
    }

    /**
     * 更新
     * @param goods
     * @return
     */
    @MethodLog(remark = "更新商品")
    public int update(ShopGoods goods) {
        goods.setDescription(goods.getDescription().replaceAll("white-space", ""));
        return shopGoodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 上、下线
     *
     * @param id
     * @return
     */
    @Transactional
    public int online(Long id, Integer online) {
        ShopGoods goods = new ShopGoods();
        goods.setId(id);
        goods.setIsOnline(online == 1);
        return shopGoodsMapper.updateByPrimaryKeySelective(goods);
    }
}
