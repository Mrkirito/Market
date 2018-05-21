package com.hangjia.bxj.newperson.service;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCourseType;
import com.hangjia.bxj.newperson.query.NewPersonCourseTypeQuery;

/**
 * Created by Administrator on 2016/10/26.
 */
public interface NewPersonCourseTypeService {

    /**
     * 分页查询
     * @return
     */
    Result getPageList(NewPersonCourseTypeQuery query);

    /**
     * 分页查询
     * @return
     */
    Result getPageListByQuery(NewPersonCourseTypeQuery query);

    /**
     * 详细
     * @return
     */
    NewPersonCourseType detail(Long id);

    /**
     * 添加
     * @param courseType
     * @return
     */
    int add(NewPersonCourseType courseType);

    /**
     * 更新
     * @param courseType
     * @return
     */
    int update(NewPersonCourseType courseType);

    /**
     * 禁用、启用
     *
     * @param id
     * @return
     */
    int forbid(Long id, Integer status);
}
