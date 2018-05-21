package com.hangjia.bxj.newperson.controller;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourse;
import com.hangjia.bxj.newperson.query.NewPersonCourseQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/10/26.
 */
@Controller
@RequestMapping("course")
public class NewPersonCourseController {

    @Autowired
    NewPersonCourseService newPersonCourseService;
    /**
     * 列表跳转
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.jhtml")
    public Object list() {
        return "newperson/course";
    }

    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(NewPersonCourseQuery query) {
        return newPersonCourseService.getPageListByQuery(query);
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        NewPersonCourse course = newPersonCourseService.detail(id);
        if(null != course){
            result.setModel(course);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加、更新
     * @param course
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute NewPersonCourse course) {
        Result result = new Result();
        int count = 0;
        if(null != course.getId())
            count = newPersonCourseService.update(course);
        else count = newPersonCourseService.add(course);
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
    @RequestMapping("forbid.json")
    @ResponseBody
    public Object forbid(Long id, Integer status) {
        Result result = new Result();
        int count = newPersonCourseService.forbid(id, status);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }
}
