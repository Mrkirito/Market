package com.hangjia.bxj.newperson.controller;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourseType;
import com.hangjia.bxj.newperson.query.NewPersonCourseTypeQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseTypeService;
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
@RequestMapping("courseType")
public class NewPersonCourseTypeController {

    @Autowired
    NewPersonCourseTypeService newPersonCourseTypeService;
    /**
     * 列表跳转
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.jhtml")
    public Object list() {
        return "newperson/course_type";
    }

    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(NewPersonCourseTypeQuery query) {
        return newPersonCourseTypeService.getPageListByQuery(query);
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        NewPersonCourseType courseType = newPersonCourseTypeService.detail(id);
        if(null != courseType){
            result.setModel(courseType);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加、更新
     * @param courseType
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute NewPersonCourseType courseType) {
        Result result = new Result();
        int count = 0;
        if(null != courseType.getId())
            count = newPersonCourseTypeService.update(courseType);
        else count = newPersonCourseTypeService.add(courseType);
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
        int count = newPersonCourseTypeService.forbid(id, status);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }
}
