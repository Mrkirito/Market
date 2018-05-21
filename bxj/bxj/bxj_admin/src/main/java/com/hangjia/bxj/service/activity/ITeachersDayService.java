package com.hangjia.bxj.service.activity;

import com.hangjia.bxj.model.activity.ActivityTeachersDayLikeStat;
import com.hangjia.bxj.model.activity.ActivityTeachersDayOwe;
import com.hangjia.bxj.query.activity.TeachersDayOweQuery;
import com.hangjia.bxj.query.activity.TeachersDayStatQuery;
import com.hangjia.bxj.ucenter.model.Profit;

import java.math.BigDecimal;
import java.util.List;

/**
 * 教师节相关
 *
 * @author: yaoy
 * @date: 2016-06-29
 */
public interface ITeachersDayService {

    /**
     * 查询感恩卡列表总数
     *
     * @param query
     * @return
     */
    int queryOweDataCount(TeachersDayOweQuery query);

    /**
     * 查询感恩卡列表
     *
     * @param query
     * @return
     */
    List<ActivityTeachersDayOwe> queryOweDataPage(TeachersDayOweQuery query);

    /**
     * 修改感恩卡
     *
     * @param id
     * @return
     */
    int updateOwe(Long id);

    /**
     * 幸运礼金
     *
     * @param id
     * @param amount
     * @return
     */
    int luckOwe(Long id, BigDecimal amount);

    /**
     * 查询感恩卡统计列表总数
     *
     * @param query
     * @return
     */
    int queryStatDataCount(TeachersDayStatQuery query);

    /**
     * 查询感恩卡统计列表
     *
     * @param query
     * @return
     */
    List<ActivityTeachersDayLikeStat> queryStatDataPage(TeachersDayStatQuery query);
}
