package com.hangjia.bxj.newperson.dao;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.newperson.model.NewPersonCoursePpt;

import java.util.List;

public interface NewPersonCoursePptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewPersonCoursePpt record);

    int insertSelective(NewPersonCoursePpt record);

    NewPersonCoursePpt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewPersonCoursePpt record);

    int updateByPrimaryKey(NewPersonCoursePpt record);

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
    List<NewPersonCoursePpt> selectByPage(BaseCommonQuery query);
}