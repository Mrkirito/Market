package com.hangjia.bxj.dao.activity;

import com.hangjia.bxj.model.activity.ActivityTeachersDayLikeStat;
import com.hangjia.bxj.query.activity.TeachersDayStatQuery;

import java.util.List;

public interface ActivityTeachersDayLikeStatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityTeachersDayLikeStat record);

    int insertSelective(ActivityTeachersDayLikeStat record);

    ActivityTeachersDayLikeStat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityTeachersDayLikeStat record);

    int updateByPrimaryKey(ActivityTeachersDayLikeStat record);

    int queryStatDataCount(TeachersDayStatQuery record);

    List<ActivityTeachersDayLikeStat> queryStatDataPage(TeachersDayStatQuery record);
}