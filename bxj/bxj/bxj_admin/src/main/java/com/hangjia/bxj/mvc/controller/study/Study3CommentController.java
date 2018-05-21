package com.hangjia.bxj.mvc.controller.study;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.query.study.Study3CommentQuery;
import com.hangjia.bxj.service.study.Study3CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
@Controller
@RequestMapping("study3Comment")
public class Study3CommentController {
    @Autowired
    Study3CommentService study3CommentService;

    /**
     * 列表跳转
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.jhtml")
    public Object list() {
        return "study/comment";
    }

    /**
     * 列表
     * @author xiongfangyong
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public Object list(Study3CommentQuery query) {
        return study3CommentService.getPageList(query);
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
        int count = study3CommentService.delete(id);
        if(count < 1){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 批量删除评论
     * @param ids
     * @return Result
     */
    @RequestMapping("delete_bat.json")
    public @ResponseBody Result deleteBat(String ids) {
        Result result = new Result();
        if(StringUtils.isNotBlank(ids)){
            int delete = study3CommentService.deleteBat(ids);
            if(delete <= 0){
                result.setSuccess(false);
            }
        } else {
            result.setSuccess(false);
        }
        return result;
    }
}
