package com.hangjia.bxj.newperson.dao;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.newperson.model.NewPersonCourseType;

import java.util.List;

public interface NewPersonCourseTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewPersonCourseType record);

    int insertSelective(NewPersonCourseType record);

    NewPersonCourseType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewPersonCourseType record);

    int updateByPrimaryKey(NewPersonCourseType record);

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
    List<NewPersonCourseType> selectByPage(BaseCommonQuery query);

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
    List<NewPersonCourseType> selectPageByQuery(BaseCommonQuery query);
}