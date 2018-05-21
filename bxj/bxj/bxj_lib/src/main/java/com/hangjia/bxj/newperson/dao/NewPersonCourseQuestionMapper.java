package com.hangjia.bxj.newperson.dao;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.newperson.model.NewPersonCourseQuestion;

import java.util.List;

public interface NewPersonCourseQuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewPersonCourseQuestion record);

    int insertSelective(NewPersonCourseQuestion record);

    NewPersonCourseQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewPersonCourseQuestion record);

    int updateByPrimaryKey(NewPersonCourseQuestion record);

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
    List<NewPersonCourseQuestion> selectByPage(BaseCommonQuery query);

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
    List<NewPersonCourseQuestion> selectPageByQuery(BaseCommonQuery query);
}