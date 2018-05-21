package com.hangjia.bxj.service.activity.impl;

import com.hangjia.bxj.dao.activity.ActivityTeachersDayLikeStatMapper;
import com.hangjia.bxj.dao.activity.ActivityTeachersDayOweMapper;
import com.hangjia.bxj.model.activity.ActivityTeachersDayLikeStat;
import com.hangjia.bxj.model.activity.ActivityTeachersDayOwe;
import com.hangjia.bxj.query.activity.TeachersDayOweQuery;
import com.hangjia.bxj.query.activity.TeachersDayStatQuery;
import com.hangjia.bxj.service.activity.ITeachersDayService;
import com.hangjia.bxj.ucenter.model.Profit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class TeachersDayServiceImpl implements ITeachersDayService {

    @Autowired
    private ActivityTeachersDayOweMapper teachersDayOweMapper;
    @Autowired
    private ActivityTeachersDayLikeStatMapper teachersDayLikeStatMapper;

    @Override
    public int queryOweDataCount(TeachersDayOweQuery query) {
        return teachersDayOweMapper.queryOweDataCount(query);
    }

    @Override
    public List<ActivityTeachersDayOwe> queryOweDataPage(TeachersDayOweQuery query) {
        List<ActivityTeachersDayOwe> list = new ArrayList<ActivityTeachersDayOwe>();
        list = teachersDayOweMapper.queryOweDataPage(query);
        return list;
    }

    /**
     * 修改感恩卡
     *
     * @param id
     * @return
     */
    @Override
    public int updateOwe(Long id) {
        ActivityTeachersDayOwe teachersDayOwe = teachersDayOweMapper.selectByPrimaryKey(id);
        if (teachersDayOwe != null) {
            if (teachersDayOwe.getOweType() == 1) {
                teachersDayOwe.setOweType(2);
                return teachersDayOweMapper.updateByPrimaryKeySelective(teachersDayOwe);
            } else if (teachersDayOwe.getOweType() == 2) {
                teachersDayOwe.setOweType(1);
                return teachersDayOweMapper.updateByPrimaryKeySelective(teachersDayOwe);
            }
        }
        return 0;
    }

    /**
     * 幸运礼金
     *
     * @param id
     * @param amount
     * @return
     */
    @Override
    public int luckOwe(Long id, BigDecimal amount) {
        ActivityTeachersDayOwe teachersDayOwe = teachersDayOweMapper.selectByPrimaryKey(id);
        if (teachersDayOwe != null) {
            ActivityTeachersDayLikeStat teachersDayLikeStat = new ActivityTeachersDayLikeStat();
            teachersDayLikeStat.setUserId(teachersDayOwe.getUserId());
            teachersDayLikeStat.setPhone(teachersDayOwe.getPhone());
            teachersDayLikeStat.setOweName(teachersDayOwe.getOweName());
            teachersDayLikeStat.setOweContent(teachersDayOwe.getOweContent());
            teachersDayLikeStat.setSignedName(teachersDayOwe.getSignedName());
            teachersDayLikeStat.setSignedPhoto(teachersDayOwe.getSignedPhoto());
            teachersDayLikeStat.setLikeNum(teachersDayOwe.getLikeNum());
            teachersDayLikeStat.setOweType(teachersDayOwe.getOweType());
            teachersDayLikeStat.setSignCreateTime(teachersDayOwe.getCreateTime());
            teachersDayLikeStat.setSignUpdateTime(teachersDayOwe.getUpdateTime());
            teachersDayLikeStat.setType(2);
            teachersDayLikeStat.setStatDate(new Date());
            teachersDayLikeStat.setAmount(amount);
            return teachersDayLikeStatMapper.insertSelective(teachersDayLikeStat);
        }
        return 0;
    }

    /**
     * 查询感恩卡统计列表总数
     *
     * @param query
     * @return
     */
    @Override
    public int queryStatDataCount(TeachersDayStatQuery query) {
        return teachersDayLikeStatMapper.queryStatDataCount(query);
    }

    /**
     * 查询感恩卡统计列表
     *
     * @param query
     * @return
     */
    @Override
    public List<ActivityTeachersDayLikeStat> queryStatDataPage(TeachersDayStatQuery query) {
        List<ActivityTeachersDayLikeStat> list = new ArrayList<ActivityTeachersDayLikeStat>();
        list = teachersDayLikeStatMapper.queryStatDataPage(query);
        return list;
    }

}
