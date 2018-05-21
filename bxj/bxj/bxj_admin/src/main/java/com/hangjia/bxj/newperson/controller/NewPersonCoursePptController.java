package com.hangjia.bxj.newperson.controller;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCoursePpt;
import com.hangjia.bxj.newperson.query.NewPersonCoursePptQuery;
import com.hangjia.bxj.newperson.query.NewPersonCourseQuery;
import com.hangjia.bxj.newperson.service.NewPersonPptService;
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
@RequestMapping("coursePpt")
public class NewPersonCoursePptController {

    @Autowired
    NewPersonPptService newPersonPptService;


    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(NewPersonCoursePptQuery query) {
        return newPersonPptService.getPageList(query);
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        NewPersonCoursePpt coursePpt = newPersonPptService.detail(id);
        if(null != coursePpt){
            result.setModel(coursePpt);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加、更新
     * @param coursePpt
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute NewPersonCoursePpt coursePpt) {
        Result result = new Result();
        int count = 0;
        if(null != coursePpt.getId())
            count = newPersonPptService.update(coursePpt);
        else count = newPersonPptService.add(coursePpt);
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
        int count = newPersonPptService.forbid(id, status);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delete.json")
    @ResponseBody
    public Object delete(Long id) {
        Result result = new Result();
        int count = newPersonPptService.delete(id);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }
}
