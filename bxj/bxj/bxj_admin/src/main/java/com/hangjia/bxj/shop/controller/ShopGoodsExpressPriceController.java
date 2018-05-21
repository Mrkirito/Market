package com.hangjia.bxj.shop.controller;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.shop.model.ShopGoodsExpressPrice;
import com.hangjia.bxj.shop.query.ShopGoodsExpressPriceQuery;
import com.hangjia.bxj.shop.service.ShopGoodsExpressPriceService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("goodsExpressPrice")
public class ShopGoodsExpressPriceController {
    @Autowired
    ShopGoodsExpressPriceService shopGoodsExpressPriceService;

    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(ShopGoodsExpressPriceQuery query) {
        return shopGoodsExpressPriceService.getPageListByGoodsId(query);
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        ShopGoodsExpressPrice expressPrice = shopGoodsExpressPriceService.detail(id);
        if(null != expressPrice){
            result.setModel(expressPrice);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加、更新
     * @param expressPrice
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute ShopGoodsExpressPrice expressPrice) {
        Result result = new Result();
        int count = 0;
        if(null != expressPrice.getId())
            count = shopGoodsExpressPriceService.update(expressPrice);
        else count = shopGoodsExpressPriceService.add(expressPrice);
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
        int count = shopGoodsExpressPriceService.delete(id);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 批量删除
     * @param ids
     * @return Result
     */
    @RequestMapping("delete_bat.json")
    public @ResponseBody Result deleteBat(String ids) {
        Result result = new Result();
        if(StringUtils.isNotBlank(ids)){
            int delete = shopGoodsExpressPriceService.deleteBat(ids);
            if(delete <= 0){
                result.setSuccess(false);
            }
        } else {
            result.setSuccess(false);
        }
        return result;
    }
}
