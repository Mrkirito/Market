package com.hangjia.bxj.newperson.controller;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourseStage;
import com.hangjia.bxj.newperson.query.NewPersonCourseStageQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 新人通课程阶段控制器
 * <p>
 * Created by bei.zhang on 2016/11/25.
 */
@Controller
@RequestMapping("courseStage")
public class NewPersonCourseStageController {

    @Autowired
    private NewPersonCourseStageService courseStageService;


    @RequestMapping("list")
    public String list() {
        return "newperson/course_stage";
    }

    /**
     * 分页查询课程阶段信息
     *
     * @param query
     * @return
     */
    @RequestMapping("queryCourseStageList")
    @ResponseBody
    public Result queryCourseStageList(@ModelAttribute NewPersonCourseStageQuery query) {
        Result result = new Result();
        int count = 1;
        if (null != query) {
            count = courseStageService.queryStageDataCount(query);
        }
        if (count > 0) {
            List<NewPersonCourseStage> profitList = courseStageService.queryStageDataPage(query);
            result.setModel(profitList);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 新增开门红文章
     *
     * @param courseStage
     * @return Result
     */
    @RequestMapping("saveCourseStage.json")
    @ResponseBody
    public Result saveCourseStage(@ModelAttribute NewPersonCourseStage courseStage) {
        Result result = new Result();
        if (courseStage.getId() == null) {
            int count = courseStageService.addCourseStage(courseStage);
            if (count <= 0) {
                result.setSuccess(false);
                result.setMsg("新增失败");
            } else {
                result.setMsg("新增成功");
            }
        } else {
            int count = courseStageService.updateCourseStage(courseStage);
            if (count <= 0) {
                result.setSuccess(false);
                result.setMsg("修改失败");
            } else {
                result.setMsg("修改成功");
            }
        }
        return result;
    }
}
