package com.hangjia.bxj.newperson.controller;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourseQuestion;
import com.hangjia.bxj.newperson.model.NewPersonCourseType;
import com.hangjia.bxj.newperson.query.NewPersonCourseQuestionQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseQuestionService;
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
@RequestMapping("courseQuestion")
public class NewPersonCourseQuestionController {

    @Autowired
    NewPersonCourseQuestionService newPersonCourseQuestionService;
    /**
     * 列表跳转
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.jhtml")
    public Object list() {
        return "newperson/course_question";
    }

    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(NewPersonCourseQuestionQuery query) {
        return newPersonCourseQuestionService.getPageListByQuery(query);
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        NewPersonCourseQuestion courseQuestion = newPersonCourseQuestionService.detail(id);
        if(null != courseQuestion){
            result.setModel(courseQuestion);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加、更新
     * @param courseQuestion
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute NewPersonCourseQuestion courseQuestion) {
        Result result = new Result();
        int count = 0;
        if(null != courseQuestion.getId())
            count = newPersonCourseQuestionService.update(courseQuestion);
        else count = newPersonCourseQuestionService.add(courseQuestion);
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
        int count = newPersonCourseQuestionService.forbid(id, status);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }
}
