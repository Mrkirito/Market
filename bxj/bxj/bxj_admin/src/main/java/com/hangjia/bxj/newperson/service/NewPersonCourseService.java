package com.hangjia.bxj.newperson.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourse;

/**
 * Created by Administrator on 2016/10/26.
 */
public interface NewPersonCourseService {

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
    NewPersonCourse detail(Long id);

    /**
     * 添加
     * @param course
     * @return
     */
    int add(NewPersonCourse course);

    /**
     * 更新
     * @param course
     * @return
     */
    int update(NewPersonCourse course);

    /**
     * 禁用、启用
     *
     * @param id
     * @return
     */
    int forbid(Long id, Integer status);
}
