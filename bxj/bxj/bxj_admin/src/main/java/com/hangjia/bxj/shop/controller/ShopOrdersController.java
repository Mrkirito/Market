/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.shop.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.excel.view.ViewExcel;
import com.hangjia.bxj.model.SalesTicketOrderDetail;
import com.hangjia.bxj.model.shop.ShopOrders;
import com.hangjia.bxj.model.shop.ShopOrdersDetail;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.shop.ShopOrdersQuery;
import com.hangjia.bxj.shop.service.ShopOrdersService;

/**
 * Created by Caigp
 * Date: 2016/11/2 10:46
 */
@Controller
@RequestMapping("shopOrders")
public class ShopOrdersController extends BaseModule {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShopOrdersService shopOrdersService;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
    /**
     * 跳转表单详情首页
     *
     * @return
     */
    @RequestMapping("list.jhtml")
    public Object list() {
        return "shop/shopOrders";
    }

    /**
     * 获取列表信息
     *
     * @param query
     * @return
     */
    @RequestMapping("queryOrderDetailsList.json")
    public
    @ResponseBody
    Result queryShopOrdersList(@ModelAttribute ShopOrdersQuery query) {
        Date date = new Date();
        if(null == query.getStartTime()){query.setStartTime(date); query.setEndTime(date);}
//        return shopOrdersService.getPageListByQuery(query);
        return shopOrdersService.getShopOrderList(query);
    }

    /**
     * 导出Excel
     * @param model
     * @param query
     * @return
     */
    @RequestMapping("exportExcel.json")
    public ModelAndView exportExcel(ModelMap model,@ModelAttribute ShopOrdersQuery query){
        Date date = new Date();
        if(null == query.getStartTime()){query.setStartTime(date); query.setEndTime(date);}
        int count = shopOrdersService.getShopOrderListCount(query);
        query.setTotalItem(count);
        ViewExcel viewExcel = new ViewExcel("订单详细信息表",getLoginUserId().longValue(),query,shopOrdersService,"getShopOrderListByExport","goodOrders");
        return new ModelAndView(viewExcel,model);
    }
    @RequestMapping("importExcel.json")
    public 	@ResponseBody Object importExcel(MultipartFile file){
        return shopOrdersService.importExcel(file);
    } 
}