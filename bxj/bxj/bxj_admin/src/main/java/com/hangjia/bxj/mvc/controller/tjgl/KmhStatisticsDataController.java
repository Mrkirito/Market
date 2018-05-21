/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.mvc.controller.tjgl;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.tjgl.KmhStatisticsData;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.tjgl.KmhStatisticsDataQuery;
import com.hangjia.bxj.service.tjgl.KmhStatisticsDataService;
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
 * 开门红控制器
 */
@Controller
@RequestMapping("/tjgl")
public class KmhStatisticsDataController extends BaseModule {

    @Autowired
    private KmhStatisticsDataService kmhStatisticsDataService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/kmhIndex.jhtml")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tjgl/KmhStatisticsDataIndex");
        return modelAndView;
    }


    @RequestMapping("/queryKmhStatisticsDataList.json")
    public @ResponseBody Result queryKmhStatisticsDataList(KmhStatisticsDataQuery query){
        return kmhStatisticsDataService.getPageListByQuery(query);
    }

    @RequestMapping("/getAllBxjAppKmhDatas.json")
    public
    @ResponseBody
    Result getAllBxjAppXrtDatas() {
        Result result = new Result();
        List<KmhStatisticsData> allBxjAppKmhDatas = kmhStatisticsDataService.getAllBxjAppKmhDatas();
        result.setModel(allBxjAppKmhDatas);
        return result;
    }
}