package com.hangjia.bxj.shop.service.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.shop.dao.ShopGoodsExpressPriceMapper;
import com.hangjia.bxj.shop.model.ShopGoodsExpressPrice;
import com.hangjia.bxj.shop.query.ShopGoodsExpressPriceQuery;
import com.hangjia.bxj.shop.service.ShopGoodsExpressPriceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
@Service
public class ShopGoodsExpressPriceServiceImpl implements ShopGoodsExpressPriceService {
    @Autowired
    ShopGoodsExpressPriceMapper expressPriceMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(BaseCommonQuery query) {
        Result result = new Result();
        int count = expressPriceMapper.selectByCount(query);
        List<ShopGoodsExpressPrice> list = expressPriceMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 分页查询
     * @return
     */
    public Result getPageListByGoodsId(ShopGoodsExpressPriceQuery query) {
        Result result = new Result();
        int count = expressPriceMapper.selectCountByGoodsId(query);
        List<ShopGoodsExpressPrice> list = expressPriceMapper.selectPageByQuery(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    public ShopGoodsExpressPrice detail(Long id) {
        return expressPriceMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加
     * @param expressPrice
     * @return
     */
    @MethodLog(remark = "添加商品快递价格")
    public int add(ShopGoodsExpressPrice expressPrice) {
        int count = 0;
        String [] ares = expressPrice.getArea().split(",");
        for(String area : ares) {
            if(StringUtils.isNotEmpty(area)) {
                ShopGoodsExpressPriceQuery query = new ShopGoodsExpressPriceQuery();
                query.setArea(area);
                query.setGoodsId(expressPrice.getGoodsId());
                List<ShopGoodsExpressPrice> list = expressPriceMapper.selectPageByQuery(query);
                if(null==list || list.size()==0) {
                    expressPrice.setArea(area);
                    count += expressPriceMapper.insertSelective(expressPrice);
                }
            }
        }
        return count;
    }

    /**
     * 更新
     * @param expressPrice
     * @return
     */
    @MethodLog(remark = "更新商品快递价格")
    public int update(ShopGoodsExpressPrice expressPrice) {
        return expressPriceMapper.updateByPrimaryKeySelective(expressPrice);
    }

    /**
     * 删除
     * @return
     */
    @MethodLog(remark = "删除商品快递价格")
    public int delete(Long id) {
        return expressPriceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除文章评论
     * @param ids
     * @return
     */
    @MethodLog(remark = "批量删除商品快递价格")
    public int deleteBat(String ids) {
        int count = 0;
        String [] idArr = ids.split(",");
        for(String id: idArr) {
            count += expressPriceMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
        return count;
    }
}
