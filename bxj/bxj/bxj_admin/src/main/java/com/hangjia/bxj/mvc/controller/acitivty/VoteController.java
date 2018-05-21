package com.hangjia.bxj.mvc.controller.acitivty;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.activity.ActivityTeachersDayOwe;
import com.hangjia.bxj.model.activity.ActivityVoteSign;
import com.hangjia.bxj.query.activity.TeachersDayOweQuery;
import com.hangjia.bxj.query.activity.VoteSignerQuery;
import com.hangjia.bxj.service.activity.IVoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 作者 : bei.zhang
 * @version 1.0
 * @date 2016年8月29日 下午2:30:15
 */
@Controller
@RequestMapping("/activity")
public class VoteController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IVoteService voteService;

    @InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 跳转页面
     * 投票活动管理
     *
     * @return url
     */
    @RequestMapping("vote.jhtml")
    public String teachersday() {
        return "activity/vote";
    }

    /**
     * 查询感恩卡列表
     *
     * @param query
     * @return Result
     */
    @RequestMapping("queryVoteSignerList.json")
    @ResponseBody
    public Result queryVoteSignerList(@ModelAttribute VoteSignerQuery query) {
        Result result = new Result();
        int count = 1;
        if (null != query) {
            count = voteService.querySignerDataCount(query);
        }
        if (count > 0) {
            List<ActivityVoteSign> profitList = voteService.querySignerDataPage(query);
            result.setModel(profitList);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }


    /**
     * 参赛信息审核通过
     *
     * @param voteSign
     * @return
     */
    @RequestMapping("approve.json")
    @ResponseBody
    public Result approve(@ModelAttribute ActivityVoteSign voteSign) {
        Result result = new Result();
        int count = voteService.approve(voteSign);
        if (count <= 0) {
            result.setSuccess(false);
            result.setMsg("修改失败");
        }
        return result;
    }

    /**
     * 参赛信息修改
     *
     * @param voteSign
     * @return
     */
    @RequestMapping("update.json")
    @ResponseBody
    public Result update(@ModelAttribute ActivityVoteSign voteSign) {
        Result result = new Result();
        int count = voteService.update(voteSign);
        if (count <= 0) {
            result.setSuccess(false);
            result.setMsg("修改失败");
        }
        return result;
    }

    @RequestMapping("refuse.json")
    @ResponseBody
    public Result refuse(Long id) {
        Result result = new Result();
        int count = voteService.refuse(id);
        if (count <= 0) {
            result.setSuccess(false);
            result.setMsg("修改失败");
        }
        return result;
    }
}
