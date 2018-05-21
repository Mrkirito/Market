package com.hangjia.bxj.mvc.controller.acitivty;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.activity.ActivityTeachersDayLikeStat;
import com.hangjia.bxj.model.activity.ActivityTeachersDayOwe;
import com.hangjia.bxj.model.order.Reward;
import com.hangjia.bxj.query.activity.TeachersDayOweQuery;
import com.hangjia.bxj.query.activity.TeachersDayStatQuery;
import com.hangjia.bxj.query.order.RewardQuery;
import com.hangjia.bxj.service.activity.ITeachersDayService;
import com.hangjia.bxj.ucenter.model.Cash;
import com.hangjia.bxj.ucenter.model.Profit;
import com.hangjia.bxj.ucenter.query.CashQuery;
import org.apache.commons.lang3.StringUtils;
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

import java.math.BigDecimal;
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
public class TeachersDayController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ITeachersDayService teachersDayService;

    @InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 跳转页面
     * 教师节活动管理
     *
     * @return url
     */
    @RequestMapping("teachersday.jhtml")
    public String teachersday() {
        return "activity/teachersDay";
    }

    /**
     * 跳转页面
     * 教师节活动获奖管理
     *
     * @return url
     */
    @RequestMapping("teachersdayStat.jhtml")
    public String teachersdayStat() {
        return "activity/teachersDayStat";
    }

    /**
     * 查询感恩卡列表
     *
     * @param query
     * @return Result
     */
    @RequestMapping("queryTeachersDayOweList.json")
    @ResponseBody
    public Result queryTeachersDayOweList(@ModelAttribute TeachersDayOweQuery query) {
        Result result = new Result();
        int count = 1;
        if (null != query) {
            count = teachersDayService.queryOweDataCount(query);
        }
        if (count > 0) {
            List<ActivityTeachersDayOwe> profitList = teachersDayService.queryOweDataPage(query);
            result.setModel(profitList);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 查询感恩卡统计列表
     *
     * @param query
     * @return Result
     */
    @RequestMapping("queryTeachersDayStatList.json")
    @ResponseBody
    public Result queryTeachersDayStatList(@ModelAttribute TeachersDayStatQuery query) {
        Result result = new Result();
        int count = 1;
        if (null != query) {
            count = teachersDayService.queryStatDataCount(query);
        }
        if (count > 0) {
            List<ActivityTeachersDayLikeStat> profitList = teachersDayService.queryStatDataPage(query);
            result.setModel(profitList);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }


    /**
     * 修改感恩卡
     *
     * @param id
     * @return
     */
    @RequestMapping("updateOwe.json")
    @ResponseBody
    public Result updateOwe(Long id) {
        Result result = new Result();
        int count = teachersDayService.updateOwe(id);
        if (count <= 0) {
            result.setSuccess(false);
            result.setMsg("修改失败");
        }
        return result;
    }

    /**
     * 幸运礼金处理
     *
     * @param id
     * @return
     */
    @RequestMapping("luckOwe.json")
    @ResponseBody
    public Result updateOwe(Long id, BigDecimal amount) {
        Result result = new Result();
        int count = teachersDayService.luckOwe(id, amount);
        if (count <= 0) {
            result.setSuccess(false);
            result.setMsg("修改失败");
        }
        return result;
    }

}
