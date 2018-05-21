package com.hangjia.bxj.newperson.dao;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.newperson.model.NewPersonCourse;

import java.util.List;

public interface NewPersonCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewPersonCourse record);

    int insertSelective(NewPersonCourse record);

    NewPersonCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewPersonCourse record);

    int updateByPrimaryKey(NewPersonCourse record);

    /**************************** extend begin here *******************************/
    /**
     * 总个数
     * @param query
     * @return
     */
    int selectByCount(BaseCommonQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<NewPersonCourse> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByQuery(BaseCommonQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<NewPersonCourse> selectPageByQuery(BaseCommonQuery query);
}