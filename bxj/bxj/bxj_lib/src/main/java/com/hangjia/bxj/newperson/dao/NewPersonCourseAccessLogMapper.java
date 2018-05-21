package com.hangjia.bxj.newperson.dao;

import com.hangjia.bxj.newperson.model.NewPersonCourseAccessLog;
import com.hangjia.bxj.newperson.query.NewPersonCourseAccessQuery;

import java.util.List;

public interface NewPersonCourseAccessLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewPersonCourseAccessLog record);

    int insertSelective(NewPersonCourseAccessLog record);

    NewPersonCourseAccessLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewPersonCourseAccessLog record);

    int updateByPrimaryKey(NewPersonCourseAccessLog record);

    int queryAccessDataCount(NewPersonCourseAccessQuery query);

    List<NewPersonCourseAccessLog> queryAccessDataPage(NewPersonCourseAccessQuery query);
}