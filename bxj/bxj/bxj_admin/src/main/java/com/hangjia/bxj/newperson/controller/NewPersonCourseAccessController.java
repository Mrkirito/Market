package com.hangjia.bxj.newperson.controller;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourseAccessLog;
import com.hangjia.bxj.newperson.query.NewPersonCourseAccessQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 新人通课程日志 控制器
 * <p>
 * Created by bei.zhang on 2016/11/25.
 */
@Controller
@RequestMapping("courseAccess")
public class NewPersonCourseAccessController {

    @Autowired
    private NewPersonCourseAccessService courseAccessService;


    @RequestMapping("list")
    public String list() {
        return "newperson/course_access";
    }

    /**
     * 分页查询课程日志信息
     *
     * @param query
     * @return
     */
    @RequestMapping("queryCourseAccessList")
    @ResponseBody
    public Result queryCourseStageList(@ModelAttribute NewPersonCourseAccessQuery query) {
        Result result = new Result();
        int count = 1;
        if (null != query) {
            count = courseAccessService.queryAccessDataCount(query);
        }
        if (count > 0) {
            List<NewPersonCourseAccessLog> profitList = courseAccessService.queryAccessDataPage(query);
            result.setModel(profitList);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

}
