package com.hangjia.bxj.dao.activity;

import com.hangjia.bxj.model.activity.ActivityTeachersDayOwe;
import com.hangjia.bxj.query.activity.TeachersDayOweQuery;

import java.util.List;

public interface ActivityTeachersDayOweMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityTeachersDayOwe record);

    int insertSelective(ActivityTeachersDayOwe record);

    ActivityTeachersDayOwe selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityTeachersDayOwe record);

    int updateByPrimaryKey(ActivityTeachersDayOwe record);

    int queryOweDataCount(TeachersDayOweQuery record);

    List<ActivityTeachersDayOwe> queryOweDataPage(TeachersDayOweQuery record);
}