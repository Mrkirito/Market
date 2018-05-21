/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.mvc.controller.tjgl;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.tjgl.XrtStatisticsData;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.tjgl.XrtStatisticsDataQuery;
import com.hangjia.bxj.service.tjgl.XrtStatisticsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Caigp
 * Date: 2016/11/10 11:46
 * 新人通控制器
 */
@Controller
@RequestMapping("/tjgl")
public class XrtStatisticsDataController extends BaseModule{

    @Autowired
    private XrtStatisticsDataService xrtStatisticsDataService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/xrtIndex.jhtml")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tjgl/XrtStatisticsDataIndex");
        return modelAndView;
    }

    @RequestMapping("/queryXrtStatisticsDataList.json")
    public @ResponseBody Result  queryXrtStatisticsDataList(XrtStatisticsDataQuery query){
        return xrtStatisticsDataService.getPageListByQuery(query);
    }

    @RequestMapping("/getAllBxjAppXrtDatas.json")
    public
    @ResponseBody
    Result getAllBxjAppXrtDatas() {
        Result result = new Result();
        List<XrtStatisticsData> allBxjAppXrtDatas = xrtStatisticsDataService.getAllBxjAppXrtDatas();
        result.setModel(allBxjAppXrtDatas);
        return result;
    }
}