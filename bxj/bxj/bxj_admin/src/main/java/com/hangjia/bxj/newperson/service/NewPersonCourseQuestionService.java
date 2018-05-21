package com.hangjia.bxj.newperson.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourseQuestion;

/**
 * Created by Administrator on 2016/10/26.
 */
public interface NewPersonCourseQuestionService {

    /**
     * 分页查询
     * @return
     */
    Result getPageList(BaseCommonQuery query);

    /**
     * 分页查询
     * @return
     */
    Result getPageListByQuery(BaseCommonQuery query);

    /**
     * 详细
     * @return
     */
    NewPersonCourseQuestion detail(Long id);

    /**
     * 添加
     * @param courseQuestion
     * @return
     */
    int add(NewPersonCourseQuestion courseQuestion);

    /**
     * 更新
     * @param courseQuestion
     * @return
     */
    int update(NewPersonCourseQuestion courseQuestion);

    /**
     * 禁用、启用
     *
     * @param id
     * @return
     */
    int forbid(Long id, Integer status);
}
