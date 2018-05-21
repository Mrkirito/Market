/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.mvc.controller.competingproductsstatistics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.BxjAppWchartData;
import com.hangjia.bxj.model.competingproductsstatistics.CompetingProductsStatisticsData;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.competingproductsstatistics.CompetingProductsStatisticsDataQuery;
import com.hangjia.bxj.service.competingproductsstatistics.CompetingProductsStatisticsDataService;

/**
 * 竞品app数据控制器
* @author yuanxin
* @date 2017年6月6日上午11:56:35
* @version <b>1.0.0</b>
 */
@Controller
@RequestMapping("/cps")
public class CompetingProductsStatisticsDataController extends BaseModule {

    @Autowired
    private CompetingProductsStatisticsDataService competingProductsStatisticsDataService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/cpsIndex.jhtml")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cps/cpsStatisticsDataIndex");
        return modelAndView;
    }


    @RequestMapping("/queryCpsStatisticsDataList.json")
    public @ResponseBody Result queryCpsStatisticsDataList(CompetingProductsStatisticsDataQuery query){
        return competingProductsStatisticsDataService.getPageListByQuery(query);
    }

    @RequestMapping("/getAllBxjAppCpsDatas.json")
    public
    @ResponseBody
    Result getAllBxjAppXrtDatas() {
        Result result = new Result();
        List<CompetingProductsStatisticsData> allBxjAppCpsDatas = competingProductsStatisticsDataService.getAllBxjAppKmhDatas();
        result.setModel(allBxjAppCpsDatas);
        return result;
    }
    
    @RequestMapping("ajaxDisplayInfo.json")
    public @ResponseBody Result ajaxDisplayInfo(String date){
    	CompetingProductsStatisticsData data = competingProductsStatisticsDataService.getCpsDatasByDate(date);
        Result result = new Result();
        if (null !=data) {
            result.setModel(data);
        }else{
            result.setSuccess(false);
            result.setMsg("nodata");
        }
            return result;
    }
    
    
   /**
    * 
    * 新增数据模板
    */
    @RequestMapping("addWchartData.json")
    @ResponseBody
    public Result addProductData(CompetingProductsStatisticsData competingProductsStatisticsData) {
        Result result = new Result();
        CompetingProductsStatisticsData productCount = competingProductsStatisticsDataService.getCpsDatasByDate(new SimpleDateFormat("yyyyMMdd").format(competingProductsStatisticsData.getDataTime()));
        if (null != productCount) {
        	competingProductsStatisticsDataService.updateByPrimaryKeySelective(competingProductsStatisticsData);
            return result;
        }
        int count = competingProductsStatisticsDataService.insertSelective(competingProductsStatisticsData);
        if (count < 1) {
            result.setSuccess(false);
            result.setMsg("新增失败");
            return result;
        }
        return result;
    }
}