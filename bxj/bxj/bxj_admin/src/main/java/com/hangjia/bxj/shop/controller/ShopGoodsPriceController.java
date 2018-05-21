package com.hangjia.bxj.shop.controller;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.shop.model.ShopGoodsPrice;
import com.hangjia.bxj.shop.query.ShopGoodsPriceQuery;
import com.hangjia.bxj.shop.service.ShopGoodsPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
@Controller
@RequestMapping("goodsPrice")
public class ShopGoodsPriceController {
    @Autowired
    ShopGoodsPriceService shopGoodsPriceService;

    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(ShopGoodsPriceQuery query) {
        return shopGoodsPriceService.getPageListByGoodsId(query);
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        ShopGoodsPrice price = shopGoodsPriceService.detail(id);
        if(null != price){
            result.setModel(price);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加、更新
     * @param price
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute ShopGoodsPrice price) {
        Result result = new Result();
        int count = 0;
        if(null != price.getId())
            count = shopGoodsPriceService.update(price);
        else count = shopGoodsPriceService.add(price);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delete.json")
    @ResponseBody
    public Object delete(Long id) {
        Result result = new Result();
        int count = shopGoodsPriceService.delete(id);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }
}
