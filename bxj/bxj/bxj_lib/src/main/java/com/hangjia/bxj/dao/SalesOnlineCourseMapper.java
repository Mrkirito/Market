package com.hangjia.bxj.dao;


import java.util.List;

import com.hangjia.bxj.model.online.SalesOnlineCourse;
import com.hangjia.bxj.model.online.SalesOnlineCourseQuery;
import com.hangjia.bxj.model.online.SalesOnlineLecturer;

public interface SalesOnlineCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SalesOnlineCourse record);

    int insertSelective(SalesOnlineCourse record);

    SalesOnlineCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SalesOnlineCourse record);

    int updateByPrimaryKey(SalesOnlineCourse record);

    /**************************** extend begin here *******************************/
    /**
     * 总个数
     * @param record
     * @return
     */
    int selectCount(SalesOnlineCourseQuery record);

    /**
     * 分页查询
     * @param record
     * @return
     */
    List<SalesOnlineCourse> selectByPage(SalesOnlineCourseQuery record);
    /**
     * 获取讲师
     * @return
     */
    List<SalesOnlineLecturer> queryAllSalesOnlineLecturer();
}