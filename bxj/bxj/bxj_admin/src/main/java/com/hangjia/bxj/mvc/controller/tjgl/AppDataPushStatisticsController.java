/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.mvc.controller.tjgl;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.tjgl.AppDataPushStatistics;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.tjgl.AppDataPushStatisticsQuery;
import com.hangjia.bxj.service.tjgl.AppDataPushStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Caigp
 * Date: 2016/11/22 12:17
 */
@Controller
@RequestMapping("/tjgl")
public class AppDataPushStatisticsController extends BaseModule {

    @Autowired
    private AppDataPushStatisticsService service;

//    private static final String avgVal = "avgVal";

    /**
     * 首页
     * @return
     */
    @RequestMapping("/appDataPushStatisticsIndex.jhtml")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tjgl/AppDataPushStatisticsIndex");
        return modelAndView;
    }

    /**
     * 查询列表数据
     * @param query
     * @return
     */
    @RequestMapping("/queryAppDataPushStatisticsDataList.json")
    public @ResponseBody
    Result queryAppDataPushStatisticsDataList(AppDataPushStatisticsQuery query){
        return service.getPageListByQuery(query);
    }

    /**
     * 新增列表数据
     * @param statistics
     * @return
     */
    @RequestMapping("/addAppDataPushStatisticsData.json")
    public @ResponseBody Result addAppDataPushStatisticsData(AppDataPushStatistics statistics){
        Result result = new Result();
        int count = service.getAppDataPushStatisticsByDate(new SimpleDateFormat("yyyyMMdd").format(statistics.getDataTime()));
        if(count >= 2) {
            result.setSuccess(false);
            result.setMsg("以超出当日可录入数据量");
            return result;
        }
        count = service.insertSelective(statistics);
        if (count < 1) {
            result.setSuccess(false);
            result.setMsg("新增失败");
            return result;
        }
        return result;
    }

    /**
     * echart 图标数据
     * @return
     */
    @RequestMapping("/getAppDataPushStatisticsEchartsData.json")
    public @ResponseBody Result getAppDataPushStatisticsEchartsData(@RequestParam(required = true) String t){
        Result result = new Result();
        List<AppDataPushStatistics> list = t.equals("AM")?service.getAmPushData():service.getPmPushData();
        result.setModel(list);
        return result;
    }
}