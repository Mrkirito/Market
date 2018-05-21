package com.hangjia.bxj.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.shop.model.ShopGoods;
import com.hangjia.bxj.shop.query.ShopGoodsQuery;
import com.hangjia.bxj.shop.service.ShopGoodsService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
@Controller
@RequestMapping("shopGoods")
public class ShopGoodsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${calculationUrl}")
    private String calculationUrl;

    @Autowired
    ShopGoodsService shopGoodsService;
    /**
     * 列表跳转
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.jhtml")
    public Object list() {
        return "shop/goods";
    }

    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(ShopGoodsQuery query) {
        return shopGoodsService.getPageListByQuery(query);
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        ShopGoods goods = shopGoodsService.detail(id);
        if(null != goods){
            result.setModel(goods);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加、更新
     * @param goods
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute ShopGoods goods) {
        Result result = new Result();
        int count = 0;
        if(null != goods.getId())
            count = shopGoodsService.update(goods);
        else count = shopGoodsService.add(goods);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 禁用、启用
     * @param id
     * @return
     */
    @RequestMapping("online.json")
    @ResponseBody
    public Object online(Long id, Integer online) {
        Result result = new Result();
        int count = shopGoodsService.online(id, online);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 创建订单
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("calculation.json")
    public Object calculation(Long userId, Long goodsId, Integer count, String area) throws Exception {
        String url = calculationUrl;
        if(null!=userId) url += "&userId=" + userId;
        if(null!=goodsId) url += "&goodsId=" + goodsId;
        if(null!=count) url += "&count=" + count;
        if(null!=area) url += "&area=" + URLEncoder.encode(area, "UTF-8") ;
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        String result = null;
        try {
            client.executeMethod(method);
            if (200 == method.getStatusCode()) {
                result = JSONObject.parse(method.getResponseBodyAsString()).toString();
            }
        } catch (Exception ex) {
            logger.debug("获取订单费用计算异常，异常原因：{}", ex.toString());
        } finally {
            //释放连接
            method.releaseConnection();
        }
        return result;
    }
}
